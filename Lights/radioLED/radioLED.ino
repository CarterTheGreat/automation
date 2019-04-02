#include <RH_ASK.h>
#include <SPI.h>
#include <FastLED.h>

//Lights-----------------------------------------------------
int type = 0;//Lights type
int address = 0;
//-----------------------------------------------------------
//Comm-------------------------------------------------------
RH_ASK driver;
char key;
String data = "";
int startInd, ind1, ind2, ind3, ind4, endInd;
int typeIn, addressIn, a, b, c;
String typeInS, addressInS, aS ,bS, cS;

//-----------------------------------------------------------

//LED--------------------------------------------------------
#define LED_PIN     6
#define NUM_LEDS    60
#define BRIGHTNESS  255
#define LED_TYPE    WS2811
#define COLOR_ORDER GRB
CRGB leds[NUM_LEDS];

#define UPDATES_PER_SECOND 100

//Colors 
//TODO: move this to other file
CRGBPalette16 currentPalette;
const TProgmemPalette16 yellow_p PROGMEM = { CRGB::Yellow};
const int YELLOW = 0;
const TProgmemPalette16 blue_p PROGMEM = { CRGB::Blue};
const int BLUE = 1;
const TProgmemPalette16 green_p PROGMEM = { CRGB::Green};
const int GREEN = 2;
const TProgmemPalette16 pink_p PROGMEM = { CRGB::Pink};
const int PINK = 3;

//Routines
TBlendType currentBlending;
int currentRoutine;
const int SOLID = 0;
const int SNAKE = 1;
const int FADE = 2;

uint8_t brightness = BRIGHTNESS;

//-------------------------------------------------------------

void setup() {
  delay( 3000 ); // power-up safety delay
  
  Serial.begin(9600);
  
  if(!driver.init())
    Serial.println("Radio driver init failed");
  
  FastLED.addLeds<LED_TYPE, LED_PIN, COLOR_ORDER>(leds, NUM_LEDS).setCorrection(TypicalLEDStrip);
  FastLED.setBrightness(BRIGHTNESS);
    
  currentPalette = yellow_p;
  currentBlending = LINEARBLEND;

  Serial.print("Starting LEDs");
}

int i =0;

static uint8_t startIndex = 0;
    
void loop(){

    uint8_t buf[24];
    uint8_t buflen = sizeof(buf);
    if (driver.recv(buf, &buflen)){
      Serial.print("Message: ");
      Serial.println((char*)buf);       
      readRadio(buf);  
    }
  /*
  startIndex ++; // Motion Speed
  
  switch(currentRoutine){
    case SOLID:
      solid();
      break;
    case SNAKE:
      snake(startIndex);
      break;
    case FADE:
      fade();
      break;
    
  }
  FastLED.show();
  */
}

void readRadio(String data){
  //Reads radio data sent
  //uint8_t buf[20];
  //uint8_t buflen = sizeof(buf);
  //while(driver.recv(buf, &buflen)){
    //Reading
    //Serial.print("Recieved: ");
    //Serial.println((char*)buf);
    //data = buf;

    //Indexing
    startInd = data.indexOf('<');
    ind1 = data.indexOf('/');
    ind2 = data.indexOf('/',ind1+1);
    ind3 = data.indexOf('/',ind2+1);
    ind4 = data.indexOf('/',ind3+1);
    endInd = data.indexOf('>');

    //String Data
    typeInS = data.substring(startInd+1,ind1);
    addressInS = data.substring(ind1+1,ind2);
    aS = data.substring(ind2+1,ind3);
    bS = data.substring(ind3+1,ind4);
    cS = data.substring(ind4+1,endInd);
    
    //Int data
    typeIn = typeInS.toInt();
    addressIn = addressInS.toInt();
    a = aS.toInt();
    b = bS.toInt();
    c = cS.toInt();
    
    if(typeIn == type && addressIn == address)
      set();
  //}

}

void set(){

  //Routine
  currentRoutine = b;
  /* I might not need this at all i might be dumb
  switch(d){
    case SOLID:
      currentRoutine = 0;
      break;
    case SNAKE:
      currentRoutine = 1;
      break;
    case FADE:
      currentRoutine = 2;
      break;
  }
  */
  //Color
  switch(c){
    case YELLOW:
      currentPalette = yellow_p;
      break;
    case BLUE:
      currentPalette = blue_p;
      break;
    case GREEN:
      currentPalette = green_p;
      break;
    case PINK:
      currentPalette = pink_p;
      break;
  }
}

// Snake/Special fill 
void snake(uint8_t colorIndex){
    uint8_t brightness = 255;
    
    for( int i = 0; i < NUM_LEDS; i++) {
        leds[i] = ColorFromPalette( currentPalette, colorIndex, brightness, currentBlending);
        //leds[i] = ColorFromPalette( currentPalette, colorIndex *2, brightness, currentBlending);
        //leds[i] = ColorFromPalette( currentPalette, colorIndex *3, brightness, currentBlending);
        //leds[i] = ColorFromPalette( currentPalette, colorIndex *4, brightness, currentBlending);
        colorIndex += 1;
    }
}

void solid(){
  
  fill_solid(currentPalette, 120, CRGB::Yellow);
}


int max = 255;
int min = 0;
String lastPoint;

void fade(){
  
  if(brightness == max)
    lastPoint = "max";
  else if(brightness == min)
    lastPoint = "min";
    
  if(lastPoint == "max")
    brightness++;
  else if(lastPoint == "min")
    brightness--;
  
}

//Custom color in HSV
/*
void setPaletteHSV()
{
    for( int i = 0; i < 16; i++) {
        currentPalette[i] = CHSV( 255, 255, 255);
    }
}
*/
//Custom color in RGB
/*
void setPaletteRBG()
{
    for( int i = 0; i < 16; i++) {
        currentPalette[i] = CRGB( 255, 255, 255);
    }
}
*/
