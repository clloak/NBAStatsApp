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

public class TeamStatsActivity extends AppCompatActivity {
    private TextView teamNameTextView;
    private TextView teamRecordTextView;
    private RecyclerView teamStatsRecyclerView;
    private TeamStatsAdapter teamStatsAdapter;
    private List<TeamStats> teamStatsList;
    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);

        teamNameTextView = findViewById(R.id.teamNameTextView);
        teamRecordTextView = findViewById(R.id.teamRecordTextView);
        teamStatsRecyclerView = findViewById(R.id.teamStatsRecyclerView);
        teamStatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        teamName = getIntent().getStringExtra("team_name");

        fetchTeamStats(teamName);
    }

    private void fetchTeamStats(String teamName) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams/statistics?name=" + teamName)
                .get()
                .addHeader("X-RapidAPI-Key", "YOUR_RAPIDAPI_KEY")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Log.e("TeamStatsActivity", "failed to fetch team stats: ", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<TeamStats>>() {
                    }.getType();
                    APIResponse<TeamStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    teamStatsList = apiResponse.getResponse();
                    runOnUiThread(() -> {
                        if (!teamStatsList.isEmpty()) {
                            TeamStats firstStat = teamStatsList.get(0);
                            teamNameTextView.setText(firstStat.getPlayerName());
                            // Assuming you have a method to get the team record
                            teamRecordTextView.setText("Team Record: 0-0"); // Replace with actual record
                        }
                        teamStatsAdapter = new TeamStatsAdapter(teamStatsList);
                        teamStatsRecyclerView.setAdapter(teamStatsAdapter);
                    });
                }
            }
        });
    }
}
