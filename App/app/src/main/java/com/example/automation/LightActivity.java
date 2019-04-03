package com.example.automation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class LightActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<Device> devices = MainActivity.devices;

    TextView text;
    Switch allLightsSwitch;
    Button submit;
    FloatingActionButton fab;

    String deviceData = "";

    ArrayList<TextView> names = new ArrayList<TextView>();
    ArrayList<Switch> power = new ArrayList<Switch>();
    ArrayList<Spinner> routines = new ArrayList<Spinner>();
    ArrayList<Spinner> colors = new ArrayList<Spinner>();

    ArrayList<LinearLayout> layouts = new ArrayList<LinearLayout>();

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_light);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Text
        text = findViewById(R.id.lightText);

        //Action button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LightActivity.this, AddActivity.class);
                LightActivity.this.startActivity(myIntent);
            }
        });

        //Action drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Build
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
                ArrayAdapter<CharSequence> routineAdapter = ArrayAdapter.createFromResource(this, R.array.routines, android.R.layout.simple_spinner_item);
                ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);

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

        //text.setText(deviceData);

        allLightsSwitch = findViewById(R.id.allLightsSwitch);
        allLightsSwitch.setTextOn("On");
        allLightsSwitch.setTextOff("Off");

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < devices.size(); i++) {
                    Light light = (Light) devices.get(i);

                    boolean lastLit = light.isLitB();
                    int lastRoutine = light.getRoutine();
                    int lastColor = light.getColor();

                    light.setLit( power.get(i).isChecked());
                    light.setRoutine( routines.get(i).getSelectedItemPosition() );
                    light.setColor( colors.get(i).getSelectedItemPosition() );

                    if(!MainActivity.sendData(light.getType(), light.getID(), light.isLit(), light.getRoutine(), light.getColor())){
                        light.setLit(lastLit);
                        light.setRoutine(lastRoutine);
                        light.setColor(lastColor);
                    }

                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_home){
            Intent myIntent = new Intent(LightActivity.this, MainActivity.class);
            LightActivity.this.startActivity(myIntent);
        }
        else if (id == R.id.nav_lights) {

        } else if (id == R.id.nav_door) {
            Intent myIntent = new Intent(LightActivity.this, DoorActivity.class);
            LightActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
