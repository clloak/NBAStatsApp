package com.example.nbastatsapp;
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

public class Homepage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SeasonsAdapter seasonsAdapter;
    private List<String> seasonsList;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);

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
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("Homepage", "Selected page: " + position);
            }
        });
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Fetch data
        fetchSeasons();
    }

    private void fetchSeasons() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-nba-v1.p.rapidapi.com/seasons")
                .get()
                .addHeader("X-RapidAPI-Key", "0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b")
                .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(Homepage.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
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
}
