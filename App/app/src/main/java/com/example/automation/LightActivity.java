package com.example.automation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class LightActivity
        extends AppCompatActivity {

    ArrayList<Device> devices = MainActivity.devices;

    TextView text;
    Switch allLightsSwitch;
    Button submit;

    String deviceData = "";

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        text = findViewById(R.id.lightText);

        for(int i = 0; i < devices.size(); i++){
            if( devices.get(i).getType() == Device.LIGHT)  {
                deviceData += devices.get(i).getName();
                deviceData += " ";
                deviceData += devices.get(i).getID();
                deviceData += "\n";
            }
        }
        text.setText(deviceData);

        allLightsSwitch = findViewById(R.id.allLightsSwitch);
        allLightsSwitch.setTextOn("On");
        allLightsSwitch.setTextOff("Off");

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < devices.size(); i++) {
                    if (devices.get(i).getType() == Device.LIGHT) {
                        if (allLightsSwitch.isChecked())
                            ((Light) devices.get(i)).on();
                        else
                            ((Light) devices.get(i)).off();
                    }
                }
            }
        });

    }

}
