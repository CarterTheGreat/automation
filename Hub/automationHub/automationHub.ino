#include <Wire.h>
#include <ADXL345.h>
#include <RH_ASK.h>
#include <SPI.h>
#include <SoftwareSerial.h>

RH_ASK driver;
SoftwareSerial slaveSerial(10,11); // RX | TX

char key;
String data = "";

void setup() {
  Serial.begin(9600);
  slaveSerial.begin(9600);

  if(!driver.init())
    Serial.print("Radio driver failed init");

}

void loop() {
  
  if (slaveSerial.available()){ 

    key = slaveSerial.read();
    //Serial.write(key);
    if(key == '>'){
      data.concat(key);
      Serial.print(data);
      sendRadio(data);
      data="";
    }
    else{ 
      data.concat(c);
    }
  }
}

void sendRadio(String message){

  const char *msg = message.c_str();

  driver.send((uint8_t *)msg, strlen(msg));
  driver.waitPacketSent();

}

