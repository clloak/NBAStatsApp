package com.example.nbastatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class NBAStats extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button continueButton = findViewById(R.id.button);
        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(NBAStats.this, Homepage.class);
            startActivity(intent);
            finish();
        });

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(NBAStats.this, Homepage.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}
