package com.example.food_reach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Find the Back to Home button
        Button btnBack = findViewById(R.id.btnBack);

        // Set onClickListener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomeActivity
                Intent intent = new Intent(DonateActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close RequestsActivity
            }
        });
    }
}
