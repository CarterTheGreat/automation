# automation
All of my automation systems branch from this centrialization now

Android app connects to hub via BT,
hub sends out 433 mhz radio signal to all connected devices - sends what app sends to hub
addressed devices read and react to hub message

Data Protocall < device type / device address / data / data / data >
Light Address <0/0-255/a/b/c>
Door Control Address <1/0-255/a/b/c>

TODO: change when data == 0 to do nothing
	1 is on 
	2 is off