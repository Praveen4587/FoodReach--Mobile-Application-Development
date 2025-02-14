package com.example.food_reach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get references to the buttons
        Button btnDonateFood = findViewById(R.id.btnDonateFood);
        Button btnViewRequests = findViewById(R.id.btnViewRequests);
        Button btnMyProfile = findViewById(R.id.btnMyProfile);  // Added button for profile navigation

        // Set onClick listeners for the buttons
        btnDonateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DonateActivity
                Intent donateIntent = new Intent(HomeActivity.this, DonateActivity.class);
                startActivity(donateIntent);
            }
        });

        btnViewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RequestsActivity
                Intent requestsIntent = new Intent(HomeActivity.this, RequestsActivity.class);
                startActivity(requestsIntent);
            }
        });

        // Set the onClickListener for the "My Profile" button
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check that MyProfileActivity is properly referenced
                Intent profileIntent = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(profileIntent);
            }
        });
    }
}
