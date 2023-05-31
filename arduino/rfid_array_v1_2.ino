#include <SPI.h>
#include <MFRC522.h>

const byte resetPin = 9;
const byte numReaders = 6;
const byte ssPins[] = {2, 4, 7, 8, 5, 3};

MFRC522 mfrc522[numReaders];

void setup()
{
  Serial.begin(9600);
  while(!Serial);

  SPI.begin();

  for (int i = 0; i < numReaders; i++) {
    mfrc522[i].PCD_Init(ssPins[i], resetPin);
    delay(200);
  }
}

void loop()
{
  Serial.print("@");

  for(uint8_t i = 0; i < numReaders; i++){
    mfrc522[i].PCD_Init();

    Serial.print(i);
    Serial.print("\;");

    if(mfrc522[i].PICC_IsNewCardPresent() && mfrc522[i].PICC_ReadCardSerial()){
      dump_byte_array(mfrc522[i].uid.uidByte, mfrc522[i].uid.size);
    } else {
      Serial.print("0");
    }

    if(i < numReaders - 1) {
      Serial.print("\!");
    }

    mfrc522[i].PICC_HaltA();
    mfrc522[i].PCD_StopCrypto1();
    delay(100);
  }
  Serial.println("\#");
}

void dump_byte_array(byte *buffer, byte bufferSize) {
    for (byte i = 0; i < bufferSize; i++) {
        Serial.print(buffer[i] < 0x10 ? " 0" : " ");
        Serial.print(buffer[i], HEX);
    }
}