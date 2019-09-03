package com.kathi.project.tracker;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView idle = findViewById(R.id.idle);
        idle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, Idle.class);
                startActivity(numbersIntent);
            }
        });


        TextView missing = findViewById(R.id.missing);
        missing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this, Missing.class);
                startActivity(familyIntent);
            }
        });


        TextView out_of_campus = findViewById(R.id.out_of_campus);
        out_of_campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, OutOfCampus.class);
                startActivity(colorsIntent);
            }
        });


        TextView tracking_log = findViewById(R.id.tracking_log);
        tracking_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, TrackingLogActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }


}
