package com.example.nbastatsapp;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Home extends AppCompatActivity {
    private static final String TAG = "NBAStats";
    // private static final String API_KEY = "YOUR_RAPIDAPI_KEY";
    private static final String API_KEY = "7bd4db697amshe7fd91a37559fe2p193867jsn2e7ddeac02dc";
    private static final String NBA_API_URL = "https://api-nba-v1.p.rapidapi.com";

    private TextView textView;

    /* @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        fetchData();
    }

     */

    private void fetchData() {
        // Example API call to get current NBA standings
        String endpoint = "/standings?league=standard&season=2024";
        String url = NBA_API_URL + endpoint;

        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
                connection.setRequestProperty("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();

                runOnUiThread(() -> {
                    textView.setText(response.toString());
                });
            } catch (IOException e) {
                Log.e(TAG, "fetchData: Error fetching data", e);
            }
        }).start();
    }
}
