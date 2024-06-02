package com.example.nbastatsapp;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
public class GameDetailActivity extends AppCompatActivity {
    private TextView gameScore, team1Name, team2Name;
    private RecyclerView recyclerView;
    private BoxScoreAdapter boxScoreAdapter;
    private List<PlayerStats> playerStatsList;
    private String gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        gameScore = findViewById(R.id.gameScore);
        team1Name = findViewById(R.id.team1Name);
        team2Name = findViewById(R.id.team2Name);
        recyclerView = findViewById(R.id.recyclerView);

        // Fetch gameId from intent
        gameId = getIntent().getStringExtra("gameId");

        // Fetch game details
        fetchGameDetails(gameId);
    }

    private void fetchGameDetails(String gameId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/games/" + gameId)
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Log.e("GameDetailActivity", "onFailure: ", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type responseType = new TypeToken<APIResponse<Game>>() {
                    }.getType();
                    APIResponse<Game> gameResponse = new Gson().fromJson(jsonData, responseType);
                    runOnUiThread(() -> {
                        Game game = gameResponse.getResponse().get(0); // Assuming only one game is fetched
                        updateUI(game);
                    });
                }
            }
        });
    }

    private void updateUI(Game game) {
        gameScore.setText(game.getHomeTeam() + " " + game.getScore() + " " + game.getAwayTeam());
        team1Name.setText(game.getHomeTeam());
        team2Name.setText(game.getAwayTeam());
        // Retrieve player stats
        playerStatsList = game.getPlayerStatsList();
        boxScoreAdapter = new BoxScoreAdapter(playerStatsList);
        recyclerView.setAdapter(boxScoreAdapter);
    }
}
