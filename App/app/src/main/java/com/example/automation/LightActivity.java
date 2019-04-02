package com.example.automation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class LightActivity
        extends AppCompatActivity {

    ArrayList<Device> devices = MainActivity.devices;

    TextView text;
    Switch allLightsSwitch;
    Button submit;

    String deviceData = "";

    ArrayList<TextView> names = new ArrayList<TextView>();
    ArrayList<Button> power = new ArrayList<Button>();

    ArrayList<LinearLayout> layouts = new ArrayList<LinearLayout>();

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_light);

        text = findViewById(R.id.lightText);

        //Build
        for(int i = 0; i < devices.size(); i++){
            if( devices.get(i).getType() == Device.LIGHT)  {
                deviceData += devices.get(i).getName();
                deviceData += " ";
                deviceData += devices.get(i).getID();
                deviceData += "\n";


                //Name text
                names.add(i, new TextView(this));
                names.get(i).setText(devices.get(i).getName());

                //Power button
                power.add(i, new Button(this));
                //power.get(i).setText( devices.get(i).getName() );

                //Params
                LayoutParams vp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                LayoutParams hp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                //LL H
                layouts.add(i, new LinearLayout(this));
                layouts.get(i).setOrientation(LinearLayout.HORIZONTAL);
                layouts.get(i).addView(names.get(i), hp);
                layouts.get(i).addView(power.get(i), hp);

                //LLV
                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
                ll.addView(layouts.get(i), vp);
                //ll.addView(names.get(i), vp);
                //ll.addView( power.get(i), vp);
            }
        }

        //Action
        for(int i = 0; i < devices.size(); i++) {
            final int iter = i;
            power.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( ((Light) devices.get(iter)).isLit() ){
                        ((Light) devices.get(iter)).off();
                        power.get(iter).setText("Off");
                    }else {
                        ((Light) devices.get(iter)).on();
                        power.get(iter).setText("On");
                    }
                }
            });
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
