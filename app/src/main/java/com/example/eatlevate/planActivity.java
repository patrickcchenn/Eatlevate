package com.example.eatlevate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class planActivity extends AppCompatActivity {
    private float BMR_f;
    private Button calculate;
    private RadioButton no;
    private RadioButton light;
    private RadioButton moderate;
    private RadioButton heavy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        calculate=findViewById(R.id.calculate_button);
        no=findViewById(R.id.no_ex);
        light=findViewById(R.id.light_ex);
        moderate=findViewById(R.id.moderate_ex);
        heavy=findViewById(R.id.heavy_ex);

        //GET BMR
        SharedPreferences sharedPreferences=planActivity.this.getSharedPreferences("BMR", MODE_PRIVATE);
        float BMRRR = sharedPreferences.getFloat("BMR", 0);
        TextView maintain = findViewById(R.id.mantain);
        TextView gain= findViewById(R.id.gain);
        TextView loss=findViewById(R.id.loss);

//        TextView texts = findViewById(R.id.BMR_);
//        texts.setText(String.valueOf(BMRRR));

//        UPDATE BMR BASED ON EXERCISE ROUTINE
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no.isChecked()){
                    BMR_f = (float) (BMRRR *1.2);
                    maintain.setText("Mantain Weight: "+String.valueOf(BMR_f));
                    gain.setText("weight loss(0.5kg/week): "+String.valueOf(BMR_f-500));
                    loss.setText("weight gain(0.5kg/week): "+String.valueOf(BMR_f+500));
                }
                else if (light.isChecked()){
                    BMR_f= (float) (BMRRR* 1.4);
                    maintain.setText("Mantain Weight: "+String.valueOf(BMR_f));
                    gain.setText("weight loss(0.5kg/week): "+String.valueOf(BMR_f-500));
                    loss.setText("weight gain(0.5kg/week): "+String.valueOf(BMR_f+500));
                }
                else if(moderate.isChecked()){
                    BMR_f= (float) (BMRRR * 1.5);
                    maintain.setText("Mantain Weight: "+String.valueOf(BMR_f));
                    gain.setText("weight loss(0.5kg/week): "+String.valueOf(BMR_f-500));
                    loss.setText("weight gain(0.5kg/week): "+String.valueOf(BMR_f+500));
                }
                else if (heavy.isChecked()){
                    BMR_f= (float) (BMRRR*1.8);
                    maintain.setText("Mantain Weight: "+String.valueOf(BMR_f));
                    gain.setText("weight loss(0.5kg/week): "+String.valueOf(BMR_f-500));
                    loss.setText("weight gain(0.5kg/week): "+String.valueOf(BMR_f+500));
                }
            }
        });






}
}