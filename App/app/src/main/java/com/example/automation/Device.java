package com.example.automation;

public class Device {

    public int LIGHTS = 0;
    public int DOOR = 1;

    int id, type;

    public Device( int type, int id){
        this.type = type;
        this.id = id;
    }

}
