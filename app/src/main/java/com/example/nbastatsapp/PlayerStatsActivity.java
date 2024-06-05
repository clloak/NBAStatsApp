package com.example.nbastatsapp;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class PlayerStatsActivity extends AppCompatActivity {
    private TextView playerNameTextView;
    private TextView teamNameTextView;
    private RecyclerView playerStatsRecyclerView;
    private PlayerStatsAdapter playerStatsAdapter;
    private List<PlayerStats> playerStatsList;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        playerNameTextView = findViewById(R.id.playerNameTextView);
        teamNameTextView = findViewById(R.id.teamNameTextView);
        playerStatsRecyclerView = findViewById(R.id.playerStatsRecyclerView);
        playerStatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerName = getIntent().getStringExtra("player_name");

        fetchPlayerStats(playerName);
    }

    private void fetchPlayerStats(String playerName) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/players/statistics?name=" + playerName)
                .get()
                .addHeader("X-RapidAPI-Key", "YOUR_RAPIDAPI_KEY")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Log.e("PlayerStatsActivity", "failed to fetch player stats: ", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<PlayerStats>>() {
                    }.getType();
                    APIResponse<PlayerStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    playerStatsList = apiResponse.getResponse();
                    runOnUiThread(() -> {
                        if (!playerStatsList.isEmpty()) {
                            PlayerStats firstStat = playerStatsList.get(0);
                            playerNameTextView.setText(firstStat.getName());
                            teamNameTextView.setText(firstStat.getTeamName());
                        }
                        playerStatsAdapter = new PlayerStatsAdapter(playerStatsList);
                        playerStatsRecyclerView.setAdapter(playerStatsAdapter);
                    });
                }
            }
        });
    }
}
