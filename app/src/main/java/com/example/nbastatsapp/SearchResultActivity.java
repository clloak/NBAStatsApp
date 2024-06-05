package com.example.nbastatsapp;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class SearchResultActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        resultTextView = findViewById(R.id.resultTextView);
        String query = getIntent().getStringExtra("query");

        // Display the search query (you can fetch and display actual data here)
        resultTextView.setText("Results for: " + query);
    }
}
