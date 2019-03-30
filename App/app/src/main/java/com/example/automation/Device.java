package com.example.automation;

public class Device {

    public static final int LIGHT = 0;
    public static final int DOOR = 1;

    String name;
    int type, id;

    public Device(String name, int type, int id){
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getType(){
        return type;
    }

    public int getid(){
        return id;
    }
}
