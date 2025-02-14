package com.example.food_reach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserName, edPassword;
    private Button btnLogin;
    private TextView tvNewUser;
    private Database db; // Database instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        edUserName = findViewById(R.id.editTextLoginUserName);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvNewUser = findViewById(R.id.textViewNewUser);

        // Initialize database with required parameters
        db = new Database(getApplicationContext()); // Corrected the instance name to 'db'

        // Handle login button click
        btnLogin.setOnClickListener(view -> loginUser());

        // Handle register button click
        tvNewUser.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = edUserName.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            if (db.login(username, password) == 1) { // Assuming 1 means login success
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                // Store username in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.apply();

                // Navigate to HomeActivity
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close login screen
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
