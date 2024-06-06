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

public class TeamStatsActivity extends AppCompatActivity {
    private TextView teamNameTextView;
    private TextView teamRecordTextView;
    private RecyclerView teamStatsRecyclerView;
    private TeamStatsAdapter teamStatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);

        teamNameTextView = findViewById(R.id.teamNameTextView);
        teamRecordTextView = findViewById(R.id.teamRecordTextView);
        teamStatsRecyclerView = findViewById(R.id.teamStatsRecyclerView);
        teamStatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String teamId = getIntent().getStringExtra("team_id");
        fetchTeamStats(teamId);
    }

    private void fetchTeamStats(String teamId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams/statistics?id=" + teamId)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(TeamStatsActivity.this, "Failed to fetch team stats", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<TeamStats>>() {}.getType();
                    APIResponse<TeamStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<TeamStats> teamStatsList = apiResponse.getResponse();

                    runOnUiThread(() -> {
                        if (!teamStatsList.isEmpty()) {
                            TeamStats firstTeamStats = teamStatsList.get(0);
                            teamNameTextView.setText(firstTeamStats.getTeamName());
                            teamRecordTextView.setText(firstTeamStats.getRecord());
                            teamStatsAdapter = new TeamStatsAdapter(teamStatsList);
                            teamStatsRecyclerView.setAdapter(teamStatsAdapter);
                        } else {
                            Toast.makeText(TeamStatsActivity.this, "No stats available for this team", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
