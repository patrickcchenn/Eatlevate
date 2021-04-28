package com.example.eatlevate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlepage, addtitle, adddesc, adddate;
    EditText titledoes, descdoes, datedoes;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keydoes = Integer.toString(doesNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlepage = findViewById(R.id.titlepage);

        addtitle = findViewById(R.id.addtitle);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);

        titledoes = findViewById(R.id.titledoes);
        descdoes = findViewById(R.id.descdoes);
        datedoes = findViewById(R.id.datedoes);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);



//        NOTIFICATION
//        private void createNotificationChannel() {
//            // Create the NotificationChannel, but only on API 26+ because
//            // the NotificationChannel class is new and not in the support library
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                CharSequence name = getString(R.string.channel_name);
//                String description = getString(R.string.channel_description);
//                int importance = NotificationManager.IMPORTANCE_DEFAULT;
//                NotificationChannel channel = new NotificationChannel("123", name, importance);
//                channel.setDescription(description);
//                // Register the channel with the system; you can't change the importance
//                // or other notification behaviors after this
//                NotificationManager notificationManager = getSystemService(NotificationManager.class);
//                notificationManager.createNotificationChannel(channel);
//
//            }
//        }


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("box").
                        child("Does" + doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("title").setValue(titledoes.getText().toString());
                        dataSnapshot.getRef().child("desc").setValue(descdoes.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(datedoes.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(keydoes);

//                        Intent intent=new Intent(NewTaskAct.this,notification.class);
//                        PendingIntent pendingIntent=PendingIntent.getBroadcast(NewTaskAct.this,0,intent,0);
//
//                        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
//                        long timeclick=System.currentTimeMillis();
//                        long netral=12*1000*60-timeclick;
//                        long datee=Integer.parseInt(datedoes.getText().toString());
//
//
//                        alarmManager.set(AlarmManager.RTC_WAKEUP,timeclick+netral+date*1000*60,pendingIntent);



                        Intent a = new Intent(NewTaskAct.this,mealActivity.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}