package com.example.automation;

public class Device {

    public static final int LIGHT = 0;
    public static final int DOOR = 1;

    String name;
    int type, ID;

    public Device(String name, int type, int ID){
        this.name = name;
        this.type = type;
        this.ID = ID;
    }

    public String getName(){
        return name;
    }

    public int getType(){
        return type;
    }

    public int getID(){
        return ID;
    }
}
