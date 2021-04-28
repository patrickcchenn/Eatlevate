package com.example.eatlevate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mealActivity extends AppCompatActivity {

    private Button add_button;

    DatabaseReference reference;
    RecyclerView meal;
    ArrayList<mealList>list;
    adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        add_button=findViewById(R.id.button_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(mealActivity.this,NewTaskAct.class);
                startActivity(a);
            }
        });

        meal = findViewById(R.id.meal);
        meal.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<mealList>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("box");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // set code to retrive data and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    mealList p = dataSnapshot1.getValue(mealList.class);
                    list.add(p);
                }
                adapter = new adapter(mealActivity.this, list);
                meal.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}