package com.example.eatlevate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText weight;
    private EditText height;
    private EditText age;
    private RadioButton male;
    private RadioButton female;
    private Button submit;
    private RadioGroup group;
    private CheckBox remember;
    private float BMR;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight =(EditText) findViewById(R.id.input_weight);
        height =(EditText) findViewById(R.id.input_height);
        age = (EditText)findViewById(R.id.input_age);
        submit = findViewById(R.id.button_submit);
        group = findViewById(R.id.radio_group);
        remember = findViewById(R.id.remember_checkbox);
        male= findViewById(R.id.button_male);
        female=findViewById(R.id.button_female);
//



        //DATA TO CHECK IF IT IS FILLED OR NOT
        weight.addTextChangedListener(infoWatcher);
        height.addTextChangedListener(infoWatcher);
        age.addTextChangedListener(infoWatcher);

        //REMEMBER ME BUTTON CODE
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if (checkbox.equals("true")) {
            Intent intent = new Intent(MainActivity.this, homeActivity.class);
            intent.putExtra("BMR", BMR);
            startActivity(intent);
        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "Please input your information", Toast.LENGTH_SHORT).show();
        }

        //HANDLES BMR
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)

            @Override
            public void onClick(View view) {
                String s_weight= weight.getText().toString();
                String s_height= height.getText().toString();
                String s_age= age.getText().toString();
//

                int i_weight = Integer.parseInt(s_weight);
                int i_height = Integer.parseInt(s_height);
                int i_age = Integer.parseInt(s_age);
                if (male.isChecked()){
                     BMR= (float) ((((10.0 * i_weight) + (6.25 * i_height)) - (5.0 * i_age)) + 5);

                }
                else if(female.isChecked()){
                     BMR= (float) (((10.0 * i_weight) + (6.25 * i_height)) - (5.0 * i_age) - 161);
                }

                //PASS BMR TO OTHER ACTIVITY

                SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences("BMR",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putFloat("BMR", BMR);
                editor.apply();


                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(intent);
            }

        });

        //MORE REMEMBER ME CODE
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });
    }


        //CHECK IF ALL FIELD IS FILLED
        private TextWatcher infoWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String weightInput=weight.getText().toString().trim();
            String heightInput=height.getText().toString().trim();
            String ageInput=age.getText().toString().trim();

            submit.setEnabled(!weightInput.isEmpty() && !heightInput.isEmpty() && !ageInput.isEmpty() && group.getCheckedRadioButtonId()!=-1);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
        };
}
