package com.example.automation;

public class Light
        extends Device{

    int type = Device.LIGHT;
    String typeS = Integer.toString(type);
    String idS;

    boolean lit = false;

    public Light(String name, int id){
        super(name, Device.LIGHT, id);
        idS = Integer.toString(id);
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

    public void on(){
        lit = true;
        MainActivity.sendData(typeS, idS, "1", "0", "0");
        //turn lights on bla bla bla
    }

    public void off(){
        lit = false;
        MainActivity.sendData(typeS, idS, "2", "0", "0");
        //turn off now
    }
}
