package com.yashraj.bloodcamp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class AddCampDetails extends AppCompatActivity {
    EditText CampName, CampAddress, PhoneNumber;
    Button btnSaveCamp;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;

    // Notification constants
    private static final String CHANNEL_ID = "camp_notification_channel";
    private static final int NOTIFICATION_ID = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp_details);

        CampName = findViewById(R.id.CampName);
        CampAddress = findViewById(R.id.CampAddress);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        btnSaveCamp = findViewById(R.id.btnSaveCamp);
        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        btnSaveCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campdetalis(); // add camp details in db
            }
        });
    }

    private void campdetalis() {
        String CampNameStr = CampName.getText().toString().trim();
        String CampAddressStr = CampAddress.getText().toString().trim();
        String PhoneNumberStr = PhoneNumber.getText().toString().trim();

        // Insert data into database
        ContentValues values = new ContentValues();
        values.put("CampName", CampNameStr);
        values.put("CampAddress", CampAddressStr);
        values.put("PhoneNumber", PhoneNumberStr);

        long newRowId = sqLiteDatabase.insert("campdetails", null, values);

        if (newRowId != -1) {
            // Notification when camp details are saved
            showNotification();

            Toast.makeText(this, "Camp Details Saved", Toast.LENGTH_SHORT).show();
            finish(); // Optionally finish this activity
        } else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to display notification
    private void showNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create NotificationChannel for devices running Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Camp Notification Channel";
            String description = "Channel for Camp Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        // Create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.iconnotify)
                .setContentTitle("New Blood Donation Camp Added")
                .setContentText("A new blood camp is here!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}


//package com.yashraj.bloodcamp;
//
//import static android.os.Build.*;
//import static android.os.Build.VERSION.*;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.core.app.NotificationCompat;
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class AddCampDetails extends AppCompatActivity {
//    EditText CampName, CampAddress, PhoneNumber;
//    Button btnSaveCamp;
//    SQLiteDatabase sqLiteDatabase;
//    DatabaseHelper dbHelper;
////    String channelid="mychannelid";
////    int NotificationID=100;
////    Notification notification;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_camp_details);
//
//        CampName = findViewById(R.id.CampName);
//        CampAddress = findViewById(R.id.CampAddress);
//        PhoneNumber = findViewById(R.id.PhoneNumber);
//        btnSaveCamp = findViewById(R.id.btnSaveCamp);
//        dbHelper = new DatabaseHelper(this);
//        sqLiteDatabase = dbHelper.getWritableDatabase();
//
//
//        btnSaveCamp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                campdetalis(); // add camp details in db
//            }
//
//
//        });
//    }
//
//
//    private void campdetalis(){
//        String CampNameStr = CampName.getText().toString().trim();
//        String CampAddressStr = CampAddress.getText().toString().trim();
//        String PhoneNumberStr = PhoneNumber.getText().toString().trim();
//
//        // Insert data into database
//        ContentValues values = new ContentValues();
//        values.put("CampName", CampNameStr);
//        values.put("CampAddress", CampAddressStr);
//        values.put("PhoneNumber", PhoneNumberStr);
//
//        long newRowId = sqLiteDatabase.insert("campdetails", null, values);
//
//        if (newRowId != 0) {
//            Toast.makeText(this, "Camp Details Save", Toast.LENGTH_SHORT).show();
//
//            finish(); // Optionally finish this activity
//        } else {
//            Toast.makeText(this, "Not Save", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
