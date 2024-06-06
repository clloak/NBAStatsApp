package com.example.nbastatsapp;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class PlayerStatsActivity extends AppCompatActivity {
    private TextView playerNameTextView;
    private TextView teamNameTextView;
    private RecyclerView playerStatsRecyclerView;
    private PlayerStatsAdapter playerStatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        playerNameTextView = findViewById(R.id.playerName);
        teamNameTextView = findViewById(R.id.teamName);
        playerStatsRecyclerView = findViewById(R.id.playerStatsRecyclerView);
        playerStatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String playerId = getIntent().getStringExtra("player_id");
        fetchPlayerStats(playerId);
    }

    private void fetchPlayerStats(String playerId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/players/statistics?id=" + playerId)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(PlayerStatsActivity.this, "Failed to fetch player stats", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<PlayerStats>>() {}.getType();
                    APIResponse<PlayerStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<PlayerStats> playerStatsList = apiResponse.getResponse();

                    runOnUiThread(() -> {
                        if (!playerStatsList.isEmpty()) {
                            PlayerStats firstPlayerStats = playerStatsList.get(0);
                            playerNameTextView.setText(firstPlayerStats.getFirstname() + " " + firstPlayerStats.getLastname());
                            teamNameTextView.setText(firstPlayerStats.getTeamName());
                            playerStatsAdapter = new PlayerStatsAdapter(playerStatsList);
                            playerStatsRecyclerView.setAdapter(playerStatsAdapter);
                        } else {
                            Toast.makeText(PlayerStatsActivity.this, "No stats available for this player", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
