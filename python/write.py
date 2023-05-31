import RPi.GPIO as GPIO
import spidev
from mfrc522 import SimpleMFRC522

SCANNER_1_RESET_PIN = 21
SCANNER_2_RESET_PIN = 20

if __name__ == "__main__":
    GPIO.setmode(GPIO.BCM)
    GPIO.setwarnings(False)

    GPIO.setup(SCANNER_1_RESET_PIN, GPIO.OUT)
    GPIO.setup(SCANNER_2_RESET_PIN, GPIO.OUT)

    resetPins = [SCANNER_1_RESET_PIN, SCANNER_2_RESET_PIN]

    reader = SimpleMFRC522()
    while True:
        for currentPin in resetPins:
            for pin in resetPins:
                GPIO.output(pin, pin == currentPin)

            reader.READER.spi = spidev.SpiDev()
            reader.READER.spi.open(0, 0)
            reader.READER.spi.max_speed_hz = 1000000
            reader.READER.MFRC522_Init()

            # reader.write_no_block("2")
            id, text = reader.read_no_block()
            reader.READER.spi.close()

            print('Pin: ' + str(currentPin) + ' ' + str(id) + ': ' + str(text))
