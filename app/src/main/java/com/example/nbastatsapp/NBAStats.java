package com.example.nbastatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class NBAStats extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define fade-out animation
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        // Apply fade-out animation to the ConstraintLayout
        findViewById(R.id.rootLayout).startAnimation(fadeOutAnimation);

        // Delayed navigation to Homepage
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(NBAStats.this, HomePage.class);
            startActivity(intent);
            // Apply fade-in animation to the transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}
