package com.example.nbastatsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpcomingFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView noGamesTextView;
    private GameAdapter gameAdapter;
    private List<Game> gameList;
    private static final String TAG = "UpcomingFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        noGamesTextView = view.findViewById(R.id.noGamesTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        gameList = new ArrayList<>();
        gameAdapter = new GameAdapter(gameList);
        recyclerView.setAdapter(gameAdapter);
        fetchUpcomingGames(); // Fetch games for the upcoming week
        return view;
    }

    private void fetchUpcomingGames() {
        Log.d(TAG, "fetchUpcomingGames: called");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")); // Set time zone to Pacific
        // 15 Days ahead
        for (int i = 0; i < 7; i++) { // Fetch games for the next 7 days
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles")); // Set time zone to Pacific
            String date = sdf.format(calendar.getTime());
            Log.d(TAG, "fetchUpcomingGames: fetching games for date: " + date);
            fetchGames(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    private void fetchGames(String date) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api-nba-v1.p.rapidapi.com/games?date=" + date).get().addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b").addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Log.d(TAG, "onResponse: jsonData = " + jsonData);
                    getActivity().runOnUiThread(() -> {
                        List<Game> games = parseJsonToGameList(jsonData);
                        if (!games.isEmpty()) {
                            noGamesTextView.setVisibility(View.GONE);
                            for (Game game : games) {
                                gameAdapter.addGame(game);
                            }
                            gameAdapter.notifyDataSetChanged();
                        } else {
                            noGamesTextView.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
                    });
                    Log.d(TAG, "onResponse: response not successful, code: " + response.code());
                    Log.d(TAG, "onResponse: response body: " + response.body().string());
                }
            }
        });
    }

    private List<Game> parseJsonToGameList(String jsonData) {
        List<Game> games = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray responseArray = jsonObject.getJSONArray("response");

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM-dd", Locale.getDefault());

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject gameObject = responseArray.getJSONObject(i);

                // Extract and format date
                String gameTime = gameObject.getJSONObject("date").getString("start");
                Date date = inputFormat.parse(gameTime);
                String formattedDate = outputFormat.format(date);

                JSONObject homeTeamObject = gameObject.getJSONObject("teams").getJSONObject("home");
                Team homeTeam = new Team(homeTeamObject.getString("name"), homeTeamObject.getString("logo"));

                JSONObject awayTeamObject = gameObject.getJSONObject("teams").getJSONObject("visitors");
                Team awayTeam = new Team(awayTeamObject.getString("name"), awayTeamObject.getString("logo"));

                Game game = new Game();
                game.setHomeTeam(homeTeam);
                game.setAwayTeam(awayTeam);
                game.setGameTime(formattedDate);

                games.add(game);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return games;
    }

    private List<String> getUpcomingDates() {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            dates.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}