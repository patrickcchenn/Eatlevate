package com.example.eatlevate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;


public class homeActivity extends AppCompatActivity {
    private Button plan;
    Button out;
    Button meal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        plan=findViewById(R.id.button_plan);
        out=findViewById(R.id.out);
        meal=findViewById(R.id.button_meal);

        Intent BMRR = getIntent();
        float BMR = BMRR.getFloatExtra("BMR", 0);


        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                finish();


            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1= new Intent(homeActivity.this, planActivity.class);
                startActivity(intent1);
            }
        });

        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this,mealActivity.class);
                startActivity(intent);
            }
        });
    }
}