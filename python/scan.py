import socket

import RPi.GPIO as GPIO
import numpy as np
import spidev
from mfrc522 import SimpleMFRC522

HOST = "127.0.0.1"
PORT = 4444

# 17 27 13 19 26
SCANNER_1_RESET_PIN = 21
SCANNER_2_RESET_PIN = 20

class Scanner:
    def __init__(self, resetPin):
        self.resetPin = resetPin

        GPIO.setup(resetPin, GPIO.OUT)

    def switch(self, state):
        GPIO.output(self.resetPin, state)

class Scanners:
    def __init__(self, resetPins):
        GPIO.setmode(GPIO.BCM)
        GPIO.setwarnings(False)

        self.scanners = []

        for resetPin in resetPins:
            self.scanners.append(Scanner(resetPin))

        self.reader = SimpleMFRC522()

    def scan(self):
        boardState = []
        for currentScanner in self.scanners:
            for scanner in self.scanners:
                scanner.switch(scanner == currentScanner)

            self.__openSpi()

            id, text = self.reader.read_no_block()
            self.reader.READER.spi.close()

            boardState.insert(currentScanner.resetPin, text)
        return boardState

    def __openSpi(self):
        self.reader.READER.spi = spidev.SpiDev()
        self.reader.READER.spi.open(0, 0)
        self.reader.READER.spi.max_speed_hz = 1000000
        self.reader.READER.MFRC522_Init()

class BoardState:
    lastState = []

    def __init__(self, rfidScanners):
        self.rfidScanners = rfidScanners

    def run(self):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.connect((HOST, PORT))

        while True:
            newState = rfidScanners.scan()
            if not np.array_equal(self.lastState, newState):
                print("scanned")
                self.lastState = newState

                string = "["
                if self.lastState:
                    for i, scannerState in enumerate(self.lastState):
                        if not scannerState:
                            scannerState = 0
                        string += "{ \"id\": \"" + str(i) + "\", \"value\": \"" + str(scannerState).strip() + "\" }"
                        if i < len(self.lastState) - 1:
                            string += ", "

                string += "]"

                body = string + "\n"
                sock.sendall(body.encode())

if __name__ == "__main__":
    resetPins1 = [SCANNER_1_RESET_PIN, SCANNER_2_RESET_PIN]
    rfidScanners = Scanners(resetPins1)
    boardState = BoardState(rfidScanners)

    boardState.run()
