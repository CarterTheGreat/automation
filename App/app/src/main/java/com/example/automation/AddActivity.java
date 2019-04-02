package com.example.automation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddActivity
        extends AppCompatActivity {

    ArrayList<Device> devices = MainActivity.devices;

    static TextView text;
    Spinner type;
    EditText deviceID, deviceName;
    Button add;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);

        //Text view
        text = findViewById(R.id.text);

        //Spinner
        type = findViewById(R.id.type);

        //Type filling
        ArrayAdapter<CharSequence> types = ArrayAdapter.createFromResource(this, R.array.deviceType, android.R.layout.simple_spinner_item);
        types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(types);

        //EditText
        deviceID = findViewById(R.id.deviceID);
        deviceName = findViewById(R.id.deviceName);

        //Button
        add = findViewById(R.id.add);
        add.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("Adding attempt");

                try {
                    int ID = Integer.parseInt(deviceID.getText().toString());
                    String name = deviceName.getText().toString();

                    //TODO: Make this check if device is already in existance

                    if (ID < 255 && ID >= 0) {
                        if (type.getSelectedItem().toString().equals("Light")) {

                            try {
                                if (devices.get(ID).getType() == Device.LIGHT) {
                                    text.setText("Device with this ID already exists");
                                }
                            } catch (Exception e) {
                                devices.add(new Light(name, ID));
                                text.setText("Added Light with ID " + ID);
                            }

                        } else if (type.getSelectedItem().toString().equals("Door")) {
                            try {
                                if (devices.get(ID).getType() == Device.DOOR) {
                                    text.setText("Device with this ID already exists");
                                }
                            } catch (Exception e) {
                                devices.add(new Door(name, ID));
                                text.setText("Added Door with ID " + ID);
                            }
                        } else
                            text.setText("No " + type.getSelectedItem().toString() + " device Added with ID " + ID);
                    } else
                        text.setText("ID is above 254");
                }catch(Exception e){
                }
            }
        });
    }

}
