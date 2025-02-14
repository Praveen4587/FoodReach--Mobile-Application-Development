package com.example.food_reach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MyProfileActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextContact, editTextDistrict, editTextPassword;
    private Button buttonSaveProfile, buttonDeleteProfile, buttonHome;  // Add buttonHome
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextContact = findViewById(R.id.editTextContact);
        editTextDistrict = findViewById(R.id.editTextDistrict);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile);
        buttonDeleteProfile = findViewById(R.id.buttonDeleteProfile);
        buttonHome = findViewById(R.id.buttonHome);  // Initialize the Home button

        db = new Database(this);  // Initialize Database with the correct constructor

        // Get the logged-in user's username from SharedPreferences
        String username = getSharedPreferences("shared_prefs", MODE_PRIVATE)
                .getString("username", "");

        if (!username.isEmpty()) {
            // Fetch user profile data from the database
            fetchUserProfile(username);
        }

        // Save profile button click
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated profile data
                String newUsername = editTextUsername.getText().toString();
                String newEmail = editTextEmail.getText().toString();
                String newContact = editTextContact.getText().toString();
                String newDistrict = editTextDistrict.getText().toString();
                String newPassword = editTextPassword.getText().toString();

                // Update user profile
                if (db.updateUserProfile(newUsername, newEmail, newContact, newDistrict, newPassword)) {
                    Toast.makeText(MyProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete profile button click
        buttonDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the user's profile
                if (db.deleteUserProfile(username)) {
                    Toast.makeText(MyProfileActivity.this, "Profile Deleted", Toast.LENGTH_SHORT).show();
                    // Redirect to LoginActivity or another screen
                    Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Close the MyProfileActivity
                } else {
                    Toast.makeText(MyProfileActivity.this, "Failed to Delete Profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Home button click
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start HomeActivity when the button is clicked
                Intent intent = new Intent(MyProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close MyProfileActivity after navigating
            }
        });
    }

    // Fetch user profile data from the database and populate the EditText fields
    private void fetchUserProfile(String username) {
        // Fetch user profile details from database
        String[] userData = db.getUserProfile(username);

        // Populate the EditText fields with user data
        if (userData != null) {
            editTextUsername.setText(userData[0]);  // Username
            editTextEmail.setText(userData[1]);     // Email
            editTextContact.setText(userData[2]);   // Contact
            editTextDistrict.setText(userData[3]);  // District
            editTextPassword.setText(userData[4]);  // Password
        } else {
            Toast.makeText(this, "User profile not found", Toast.LENGTH_SHORT).show();
        }
    }
}
