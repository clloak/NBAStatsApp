package com.example.nbastatsapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private EditText searchInput;
    private Button searchButton;
    private String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);

        // Get the search type from the intent
        searchType = getIntent().getStringExtra("searchType");

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim();
            if (!query.isEmpty()) {
                if (searchType.equals("player")) {
                    searchPlayer(query);
                } else if (searchType.equals("team")) {
                    searchTeam(query);
                }
            } else {
                Toast.makeText(SearchActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchPlayer(String query) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/players?search=" + query)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SearchActivity.this, "Failed to fetch player data", Toast.LENGTH_SHORT).show();
                    Log.e("SearchActivity", "failed to fetch player data: ", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<PlayerStats>>() {
                    }.getType();
                    APIResponse<PlayerStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<PlayerStats> playerStatsList = apiResponse.getResponse();

                    runOnUiThread(() -> {
                        if (playerStatsList == null || playerStatsList.isEmpty()) {
                            Toast.makeText(SearchActivity.this, "No Players Found", Toast.LENGTH_SHORT).show();
                        } else {
                            PlayerStats playerStats = playerStatsList.get(0); // Just take the first result for simplicity
                            Intent intent = new Intent(SearchActivity.this, PlayerStatsActivity.class);
                            intent.putExtra("player_id", playerStats.getId());
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private void searchTeam(String query) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams?search=" + query)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SearchActivity.this, "Failed to fetch team data", Toast.LENGTH_SHORT).show();
                    Log.e("SearchActivity", "failed to fetch team data: ", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<Team>>() {
                    }.getType();
                    APIResponse<Team> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<Team> teamList = apiResponse.getResponse();

                    runOnUiThread(() -> {
                        if (teamList == null || teamList.isEmpty()) {
                            Toast.makeText(SearchActivity.this, "No Teams Found", Toast.LENGTH_SHORT).show();
                        } else {
                            Team team = teamList.get(0); // Just take the first result for simplicity
                            Intent intent = new Intent(SearchActivity.this, TeamStatsActivity.class);
                            intent.putExtra("team_id", team.getId());
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}
