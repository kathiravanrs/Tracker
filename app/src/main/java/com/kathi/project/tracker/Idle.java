/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kathi.project.tracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Idle extends AppCompatActivity {
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    LogAdapter mLogAdapter;
    NotificationCompat.Builder builder;
    ListView mListView;
    ChildEventListener mChildEventListener;
    NotificationManager notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);
        createNotificationChannel();

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("idle");
//        Log logMessage = new Log("here", "him", "now");
//        mMessagesDatabaseReference.push().setValue(logMessage);

        mListView = findViewById(R.id.activity_idle);

        List<Log> logMessages = new ArrayList<>();
        mLogAdapter = new LogAdapter(this, R.layout.list_item, logMessages);
        mListView.setAdapter(mLogAdapter);
        attachDatabaseReadListener();


        builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.x_icon)
                .setContentTitle("Missing")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

    }




    private void attachDatabaseReadListener(){

        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log logMessage = dataSnapshot.getValue(Log.class);
                    mLogAdapter.insert(logMessage, 0);
                    builder.setContentText("Name: " + logMessage.getName().toUpperCase());
                    notificationManager.notify(2, builder.build());


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log logMessage = dataSnapshot.getValue(Log.class);
                    mLogAdapter.insert(logMessage, 0);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log logMessage = dataSnapshot.getValue(Log.class);
                    mLogAdapter.remove(logMessage);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = ("channel_name");
            String description = ("channel_description");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }


}
