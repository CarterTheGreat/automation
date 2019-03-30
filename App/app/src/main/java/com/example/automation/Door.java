package com.example.automation;

public class Door
        extends Device{

    int type = Device.DOOR;

    public Door(String name, int ID){
        super(name, Device.DOOR, ID);
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

    public void open(){
        //open door stupp
    }

    public void close(){
        //close door stuff
    }
}
