package com.example.automation;

public class Door
        extends Device{

    int type = Device.DOOR;

    public Door(String name, int id){
        super(name, Device.LIGHT, id);
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

    public void open(){
        //open door stupp
    }

    public void close(){
        //close door stuff
    }
}
