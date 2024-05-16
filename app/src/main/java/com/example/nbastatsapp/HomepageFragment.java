package com.example.nbastatsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class HomepageFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);

        Button buttonUpcomingInfo = findViewById(R.id.button_upcoming_info);
        buttonUpcomingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to Home
                Intent intent = new Intent(HomepageFragment.this, Home.class);

                intent.putExtra("gameId", "12345");

                // Start the activity
                startActivity(intent);
            }
        });
    }
}
