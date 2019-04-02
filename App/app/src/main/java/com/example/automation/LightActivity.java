package com.example.automation;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
    ArrayList<Switch> power = new ArrayList<Switch>();
    ArrayList<Spinner> routines = new ArrayList<Spinner>();
    ArrayList<Spinner> colors = new ArrayList<Spinner>();

    ArrayAdapter<CharSequence> routineAdapter = ArrayAdapter.createFromResource(this, R.array.routines, android.R.layout.simple_spinner_item);
    ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);

    ArrayList<LinearLayout> layouts = new ArrayList<LinearLayout>();

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_light);

        text = findViewById(R.id.lightText);

        //Build
        //TODO: on build, set vals to correct
        //TODO: get(i) is refrencing object one less than self
        for(int i = 0; i < devices.size(); i++){
            if( devices.get(i).getType() == Device.LIGHT)  {
                deviceData += devices.get(i).getName();
                deviceData += " ";
                deviceData += devices.get(i).getID();
                deviceData += "\n";
                deviceData += ((Light) devices.get(i)).getRoutine();
                deviceData += "\n";
                deviceData += ((Light) devices.get(i)).getColor();
                deviceData += "\n";


                //Params
                LayoutParams op = new LayoutParams(50,30); // Width , height
                LayoutParams vp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                LayoutParams hp = new LayoutParams(275, LayoutParams.WRAP_CONTENT);

                //Name text
                names.add(i, new TextView(this));
                names.get(i).setLayoutParams(op);
                names.get(i).setText(devices.get(i).getName());

                //Power switch
                power.add(i, new Switch(this));
                power.get(i).setLayoutParams(op);
                power.get(i).setChecked( ((Light) devices.get(i)).isLitB() );

                //Routine
                routines.add(i, new Spinner(this));
                routineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                routines.get(i).setAdapter(routineAdapter);
                routines.get(i).setSelection( ((Light) devices.get(i)).getRoutine() );

                //Color
                colors.add(i, new Spinner(this));
                colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                colors.get(i).setAdapter(colorAdapter);
                colors.get(i).setSelection( ((Light) devices.get(i)).getColor());

                //LL H
                layouts.add(i, new LinearLayout(this));
                layouts.get(i).setOrientation(LinearLayout.HORIZONTAL);
                layouts.get(i).addView(names.get(i), hp);
                layouts.get(i).addView(power.get(i), hp);
                layouts.get(i).addView(routines.get(i), hp);
                layouts.get(i).addView(colors.get(i), hp);

                //LLV
                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(layouts.get(i), vp);
            }
        }

        //Action
        for(int i = 0; i < devices.size(); i++) {
            final int iter = i;
            power.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*
                    if( ((Light) devices.get(iter)).isLit() == 1 ){
                        ((Light) devices.get(iter)).setLit(false);
                        power.get(iter).setText("Off");
                    }else {
                        ((Light) devices.get(iter)).setLit(true);
                        power.get(iter).setText("On");
                    }
                    */



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
                    Light light = (Light) devices.get(i);

                    light.setLit( power.get(i).isChecked());
                    //light.setRoutine( routineAdaptor.indexOf(routines.get(i).toString()));
                    light.setRoutine(   routineAdapter.getItemId(   ) );
                    //light.setColor( colorAdaptor.indexOf(colors.get(i).toString()));

                    MainActivity.sendData(light.getType(), light.getID(), light.isLit(), light.getRoutine(), light.getColor());
                }
            }
        });

    }

}
