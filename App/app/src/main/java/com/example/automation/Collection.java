package com.example.automation;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class Collection extends Application{

    static BluetoothSPP bluetooth = MainActivity.bluetooth;
    static TextView console = MainActivity.console;

    public static ArrayList<Device> devices = new ArrayList<>();

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
}
