package com.example.automation;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class Collection extends Application {

    static BluetoothSPP bluetooth = MainActivity.bluetooth;
    static TextView console = MainActivity.console;

    public static ArrayList<Device> devices = new ArrayList<Device>();

    private static Application sApplicaiton;
    private static Context context;

    //Storage
    public static void saveDevices(){
        saveArrayListToSD(getContext(), "devices", devices);
    }

    public static <E> void saveArrayListToSD(Context mContext, String filename, ArrayList<E> list){
        try {
            FileOutputStream fos = mContext.openFileOutput(filename + ".dat", mContext.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readArrayListFromSD(Context mContext,String filename){
        try {
            FileInputStream fis = mContext.openFileInput(filename + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj= (Object) ois.readObject();
            fis.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }

    //BT
    public static boolean sendData(String type, String id, String a, String b, String c){

        //TODO: make boolean and set buttons only to change if sent
        String send = "<" + type + "/" + id + "/" + a + "/" + b + "/" + c + ">";
        String data = "test";
        if(bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
            bluetooth.send(send, true);
            console.setText("Sent: " + send);
            return true;
        }else {
            console.setText("Attempted to send: " + send + " but was not connected");
            return false;
        }
    }

    public static boolean sendData(int type, int id, int a, int b, int c){

        //TODO: make boolean and set buttons only to change if sent
        String send = "<" + type + "/" + id + "/" + a + "/" + b + "/" + c + ">";
        String data = "test";
        if(bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
            bluetooth.send(send, true);
            console.setText("Sent: " + send);
            return true;
        }else {
            console.setText("Attempted to send: " + send + " but was not connected");
            return false;
        }
    }

    //Context
    public void onCreate(){
        super.onCreate();
        sApplicaiton = this;
    }

    public static Application getApplication(){
        return sApplicaiton;
    }

    public static Context getContext(){
        return Collection.context;
    }

}
