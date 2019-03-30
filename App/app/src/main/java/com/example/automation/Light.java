package com.example.automation;

public class Light
        extends Device{

    int type = Device.LIGHT;
    String typeS = Integer.toString(type);

    String s = "/";

    boolean lit = false;

    public Light(String name, int id){
        super(name, Device.LIGHT, id);
    }

    public String getNsame(){
        return name;
    }

    public int getType(){
        return type;
    }

    public int getid(){
        return id;
    }

    public void on(){
        lit = true;
        MainActivity.sendData(typeS+ s + id+ s + "1"+ s + "0"+ s + "0");
        //turn lights on bla bla bla
    }

    public void off(){
        lit = false;
        MainActivity.sendData(typeS+ s + id+ s + "2"+ s + "0"+ s + "0");
        //turn off now
    }
}
