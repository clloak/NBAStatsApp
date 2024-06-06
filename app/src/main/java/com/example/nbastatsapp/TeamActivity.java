package com.example.nbastatsapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
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
public class TeamActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TeamPagerAdapter teamPagerAdapter;
    private TextView teamNameTextView, teamRecordTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fetchTeams();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            // Handle click on the Home button
            startActivity(new Intent(this, HomePage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void fetchTeams() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams")
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Log.e("TeamActivity", "Failed to fetch teams: ", e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<Team>>() {}.getType();
                    APIResponse<Team> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<Team> teams = apiResponse.getResponse();

                    runOnUiThread(() -> setupTabs(teams));
                } else {
                    runOnUiThread(() -> Log.e("TeamActivity", "Failed to fetch teams: " + response.message()));
                }
            }
        });
    }

    private void setupTabs(List<Team> teams) {
        for (Team team : teams) {
            tabLayout.addTab(tabLayout.newTab().setText(team.getName()));
        }

        teamPagerAdapter = new TeamPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), teams);
        viewPager.setAdapter(teamPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                // fetchTeamStats(teams.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void fetchTeamStats(Team team) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams/statistics?id=" + team.getId())
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Log.e("TeamActivity", "Failed to fetch team stats: ", e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<TeamStats>>() {}.getType();
                    APIResponse<TeamStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<TeamStats> teamStatsList = apiResponse.getResponse();

                    runOnUiThread(() -> displayTeamStats(teamStatsList));
                } else {
                    runOnUiThread(() -> Log.e("TeamActivity", "Failed to fetch team stats: " + response.message()));
                }
            }
        });
    }

    private void displayTeamStats(List<TeamStats> teamStatsList) {
        if (teamStatsList != null && !teamStatsList.isEmpty()) {
            TeamStats teamStats = teamStatsList.get(0);
            teamNameTextView.setText(teamStats.getTeamName());
            teamRecordTextView.setText(String.format("Record: %d-%d", teamStats.getWins(), teamStats.getLosses()));

            // Here you should populate your RecyclerView with the stats data
            // Update the adapter of your RecyclerView to display the stats
        } else {
            Log.e("TeamActivity", "No team stats found");
        }
    }
}
