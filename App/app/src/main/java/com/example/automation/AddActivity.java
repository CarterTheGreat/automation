package com.example.automation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    EditText deviceID;
    Button add;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
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

        //Button
        add = findViewById(R.id.add);
        add.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                text.setText("Adding attempt");

                int ID = Integer.parseInt( deviceID.getText().toString() );


                //TODO: change name in to an entered value and add edit text on activity_add for this
                if(type.getSelectedItem().toString().equals("Light") ) {
                    devices.add(new Light("Light", ID));
                    text.setText("Added Light with ID "+ID);
                }
                else if(type.getSelectedItem().toString().equals("Door") ) {
                    devices.add(new Door("Door", ID));
                    text.setText("Added Door with ID "+ID);
                }
                else
                    text.setText("No "+ type.getSelectedItem().toString()+" device Added with ID "+ ID);
            }
        });
    }
}
