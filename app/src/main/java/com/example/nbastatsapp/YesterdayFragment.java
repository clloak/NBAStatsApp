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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class YesterdayFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView noGamesTextView;
    private GameAdapter gameAdapter;
    private List<Game> gameList = new ArrayList<>();
    private static final String TAG = "YesterdayFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        noGamesTextView = view.findViewById(R.id.noGamesTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchGames(getYesterdayDate()); // Enter Yesterday's Date
        return view;
    }

    private void fetchGames(String date) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/games?date=" + date)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e); // Handle failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Log.d(TAG, "onResponse: jsonData = " + jsonData);
                    List<Game> games = parseJsonToGameList(jsonData);
                    getActivity().runOnUiThread(() -> {
                        if (games.isEmpty()) {
                            noGamesTextView.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            noGamesTextView.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            gameList.addAll(games);
                            gameAdapter = new GameAdapter(games, getContext());
                            recyclerView.setAdapter(gameAdapter);
                        }
                    });
                }else {
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

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject gameObject = responseArray.getJSONObject(i);

                // Extract data
                String gameTime = gameObject.getJSONObject("date").getString("start");

                JSONObject homeTeamObject = gameObject.getJSONObject("teams").getJSONObject("home");
                Team homeTeam = new Team(homeTeamObject.getString("name"), homeTeamObject.getString("logo"));

                JSONObject awayTeamObject = gameObject.getJSONObject("teams").getJSONObject("visitors");
                Team awayTeam = new Team(awayTeamObject.getString("name"), awayTeamObject.getString("logo"));

                Game game = new Game();
                game.setHomeTeam(homeTeam);
                game.setAwayTeam(awayTeam);
                game.setGameTime(gameTime);

                games.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return games;
    }
    private String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}

