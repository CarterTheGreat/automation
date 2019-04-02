package com.example.automation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DoorActivity
        extends AppCompatActivity {

    TextView doorText;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_door);

        //doorText.findViewById(R.id.doorText);
        //doorText.setText("Coming soon");

    }

}
