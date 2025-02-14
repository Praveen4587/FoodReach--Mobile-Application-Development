package com.example.food_reach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextRegUserName, editTextRegEmail, editTextRegContact, editTextRegDistrict, editTextRegPassword, editTextRegConfirmPassword;
    Button buttonRegister, buttonSignIn;
    private Database db; // Database instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize UI components
        editTextRegUserName = findViewById(R.id.editTextRegUserName);
        editTextRegEmail = findViewById(R.id.editTextRegEmail);
        editTextRegContact = findViewById(R.id.editTextRegContact);
        editTextRegDistrict = findViewById(R.id.editTextRegDistrict);
        editTextRegPassword = findViewById(R.id.editTextRegPassword);
        editTextRegConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonSignIn = findViewById(R.id.buttonRegister2);

        // Initialize the database
        db = new Database(this);

        // Register Button Click
        buttonRegister.setOnClickListener(v -> {
            String username = editTextRegUserName.getText().toString();
            String email = editTextRegEmail.getText().toString();
            String contact = editTextRegContact.getText().toString();
            String district = editTextRegDistrict.getText().toString();
            String password = editTextRegPassword.getText().toString();
            String confirmPassword = editTextRegConfirmPassword.getText().toString();

            // Validation checks
            if (username.isEmpty() || email.isEmpty() || contact.isEmpty() || district.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Register the user in the database
                db.register(username, email, contact, district, password);
                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                // Navigate to Login Activity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Sign In Button Click (Navigate to Login Screen)
        buttonSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
