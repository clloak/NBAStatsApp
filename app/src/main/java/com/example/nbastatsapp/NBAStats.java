package com.example.nbastatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class NBAStats extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handler to navigate to the home activity after the splash screen delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(NBAStats.this, Home.class); // Change to the appropriate activity
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);

        // Click listener for the "click to continue" button
        Button continueButton = findViewById(R.id.button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the home activity
                Intent intent = new Intent(NBAStats.this, Home.class); // Change to the appropriate activity

                // Start the activity with the intent
                startActivity(intent);
            }
        });
    }
}
