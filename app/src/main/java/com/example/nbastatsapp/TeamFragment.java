package com.example.nbastatsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class TeamFragment extends Fragment {
    private static final String ARG_TEAM_ID = "team_id";
    private static final String ARG_TEAM_NAME = "team_name";

    private int teamId;
    private String teamName;
    private TextView teamNameTextView, pointsTextView, assistsTextView,
            stealsTextView, blocksTextView, turnoversTextView;

    public TeamFragment() {
        // Required empty public constructor
    }

    public static TeamFragment newInstance(int teamId, String teamName) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TEAM_ID, teamId);
        args.putString(ARG_TEAM_NAME, teamName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamId = getArguments().getInt(ARG_TEAM_ID);
            teamName = getArguments().getString(ARG_TEAM_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        teamNameTextView = view.findViewById(R.id.teamName);
        pointsTextView = view.findViewById(R.id.pointsTextView);
        assistsTextView = view.findViewById(R.id.assistsTextView);
        stealsTextView = view.findViewById(R.id.stealsTextView);
        blocksTextView = view.findViewById(R.id.blocksTextView);
        turnoversTextView = view.findViewById(R.id.turnoversTextView);

        fetchTeamStats();

        return view;
    }

    private void fetchTeamStats() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/teams/statistics?id=" + teamId + "&season=2023")
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> Log.e("TeamFragment", "Failed to fetch team stats: ", e));
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<TeamStats>>() {}.getType();
                    APIResponse<TeamStats> apiResponse = new Gson().fromJson(jsonData, responseType);
                    List<TeamStats> teamStatsList = apiResponse.getResponse();

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> displayTeamStats(teamStatsList));
                    }
                } else {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> Log.e("TeamFragment", "Failed to fetch team stats: " + response.message()));
                    }
                }
            }
        });
    }

    private void displayTeamStats(List<TeamStats> teamStatsList) {
        if (teamStatsList != null && !teamStatsList.isEmpty()) {
            TeamStats teamStats = teamStatsList.get(0);
            teamNameTextView.setText(teamName);
            pointsTextView.setText(String.format("Points: %d", teamStats.getPoints()));
            assistsTextView.setText(String.format("Assists: %d", teamStats.getAssists()));
            stealsTextView.setText(String.format("Steals: %d", teamStats.getSteals()));
            blocksTextView.setText(String.format("Blocks: %d", teamStats.getBlocks()));
            turnoversTextView.setText(String.format("Turnovers: %d", teamStats.getTurnovers()));
        } else {
            Log.e("TeamFragment", "No team stats found");
        }
    }
}
