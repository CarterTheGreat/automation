package com.example.automation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity
        extends AppCompatActivity {


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
        
    }
}
