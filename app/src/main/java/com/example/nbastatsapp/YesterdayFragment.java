package com.example.nbastatsapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
public class YesterdayFragment extends Fragment {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchGames("2024-05-31"); // Enter Yesterday's Date
        return view;
    }

    private void fetchGames(String date) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/games?date=" + date)
                .get()
                .addHeader("X-RapidAPI-Key", "YOUR_API_KEY")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    // Parse JSON and update UI
                    // Assume GameAdapter and Game class are correctly implemented
                    List<Game> games = parseJsonToGameList(jsonData);
                    getActivity().runOnUiThread(() -> {
                        gameAdapter = new GameAdapter(games);
                        recyclerView.setAdapter(gameAdapter);
                    });
                }
            }
        });
    }

    private List<Game> parseJsonToGameList(String jsonData) {
        // Implement JSON parsing logic
        return null; // Replace with actual parsed list
    }
}
