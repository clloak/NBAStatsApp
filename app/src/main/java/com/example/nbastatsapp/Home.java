package com.example.nbastatsapp;
/*
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

 */

public class Home {
   /* private RecyclerView recyclerView;
    private SeasonsAdapter seasonsAdapter;
    private List<String> seasonsList;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        // Setup TabLayout and ViewPager2
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Yesterday");
                    break;
                case 1:
                    tab.setText("Today");
                    break;
                case 2:
                    tab.setText("Upcoming");
                    break;
            }
        }).attach();

        // Initialize RecyclerView (assuming it's part of one of the fragments managed by ViewPager)
        recyclerView = findViewById(R.id.recyclerView);

        // Fetch data
        fetchSeasons();
        fetchGames("2024-06-01");  // Example date
    }

    private void fetchSeasons() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/seasons")
                .get()
                .addHeader("X-RapidAPI-Key", "YOUR_API_KEY")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(Homepage.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    Log.e("Homepage", "onFailure: ", e);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Type listType = new TypeToken<SeasonsResponse>() {}.getType();
                    SeasonsResponse seasonsResponse = new Gson().fromJson(jsonData, listType);

                    runOnUiThread(() -> {
                        seasonsList = seasonsResponse.getResponse();
                        seasonsAdapter = new SeasonsAdapter(seasonsList);
                        recyclerView.setAdapter(seasonsAdapter);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(Homepage.this, "Failed to get data", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void fetchGames(String date) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<APIResponse<Game>> call = apiService.getGames(date);

        call.enqueue(new Callback<APIResponse<Game>>() {
            @Override
            public void onResponse(Call<APIResponse<Game>> call, Response<APIResponse<Game>> response) {
                if (response.isSuccessful()) {
                    List<Game> gameList = response.body().getResponse();
                    GameAdapter gameAdapter = new GameAdapter(gameList);
                    recyclerView.setAdapter(gameAdapter);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Game>> call, Throwable t) {
                runOnUiThread(() -> Toast.makeText(Homepage.this, "Failed to fetch games", Toast.LENGTH_SHORT).show());
                Log.e("Homepage", "fetchGames onFailure: ", t);
            }
        });
    }


    */
}
