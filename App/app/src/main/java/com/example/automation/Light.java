package com.example.automation;

public class Light
        extends Device{

    int type = Device.LIGHT;
    String typeS = Integer.toString(type);
    String idS;

    final static int BABY_BLUE = 0;
    final static int PALE_PINK = 1;
    final static int PALE_YELLOW = 2;

    final static int SOLID = 0;
    final static int SNAKE = 1;
    final static int FADE = 2;


    int routine = FADE;
    int color = BABY_BLUE;

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

    public void setRoutine(int r){
        routine = r;
    }

    public int getRoutine(){
        return routine;
    }

    public void setColor(int c){
        color = c;
    }

    public int getColor(){
        return color;
    }

    public void setLit(boolean l){
        lit = l;
    }

    public boolean isLitB(){
        return lit;
    }

    public int isLit(){
        if(lit)
            return 1;
        else
            return 0;
    }
}
