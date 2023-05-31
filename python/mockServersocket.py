import socket

HOST = "127.0.0.1"
PORT = 4444

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))


while True:
    print("scannerId: ")
    scannerId = input()
    print("scannerValue: ")
    scannerValue = input()

    body = "[{id: " + str(scannerId) + ", value: " + str(scannerValue) + "}]\n"

    if (scannerId == "99") :
        body = "[{id: 1, value: 0}," \
               "{id: 2, value: 0}," \
               "{id: 3, value: 0}," \
               "{id: 4, value: 0}," \
               "{id: 5, value: 0}," \
               "{id: 6, value: 0}," \
               "{id: 7, value: 0}," \
               "{id: 8, value: 0}," \
               "{id: 9, value: 0}," \
               "{id: 10, value: 0}" \
               "]\n"

    sock.sendall(body.encode())

    print(body)
