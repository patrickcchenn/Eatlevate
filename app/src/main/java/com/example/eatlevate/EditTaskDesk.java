package com.example.eatlevate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes,descDoes,dateDoes;
    Button  btnSaveUpdate,btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes=findViewById(R.id.titledoes);
        descDoes=findViewById(R.id.descdoes);
        dateDoes=findViewById(R.id.datedoes);

        btnSaveUpdate=findViewById(R.id.btnSaveUpdate);
        btnDelete=findViewById(R.id.btnDelete);

        titleDoes.setText(getIntent().getStringExtra("title"));
        descDoes.setText(getIntent().getStringExtra("desc"));
        dateDoes.setText(getIntent().getStringExtra("date"));

        final String key=getIntent().getStringExtra("key");
        reference = FirebaseDatabase.getInstance().getReference().child("box").
                child("Does" + key);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent a =new Intent(EditTaskDesk.this,mealActivity.class);
                            startActivity(a);
                        } else{
                            Toast.makeText(getApplicationContext(),"failure!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("title").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("desc").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(dateDoes.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);
                        Intent a = new Intent(EditTaskDesk.this,MainActivity.class);
                        startActivity(a);
            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
});
    }
}
