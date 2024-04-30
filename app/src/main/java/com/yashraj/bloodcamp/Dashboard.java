package com.yashraj.bloodcamp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    Button btnDonor, btnAboutBloodCamp, btnAddDonor, btnAddCampDetails, btnCampList, btnlogout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnDonor = findViewById(R.id.btnDonor);
        // Set click listener for Donor button
        btnDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Donor.class);
                startActivity(i);
            }
        });
        btnAboutBloodCamp = findViewById(R.id.btnAboutBloodCamp);
        // Set click listener for Receiver button
        btnAboutBloodCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AboutBloodCamp.class);
                startActivity(i);
            }
        });

        btnAddDonor = findViewById(R.id.btnAddDonor);
        // Set click listener for AddDonor button
        btnAddDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddDonor.class);
                startActivity(i);
            }
        });

        btnAddCampDetails = findViewById(R.id.btnAddCampDetails);
        // Set click listener for AddCampDetails button
        btnAddCampDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddCampDetails.class);
                startActivity(i);
            }
        });
        btnCampList = findViewById(R.id.btnCampList);
        // Set click listener for Camplist button
        btnCampList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CampList.class);
                startActivity(i);
            }
        });
        btnlogout = findViewById(R.id.btnlogout);
// Set click listener for Logout button
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform logout operation here
                logout();
            }
        });
        

    }

    private void logout() {
        // Add your logout logic here, such as clearing session data, etc.

        // For example, if you're using SharedPreferences to store session data:
        SharedPreferences preferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // After logout, navigate back to the login screen or any other desired activity
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to Dashboard on pressing back button
    }

}