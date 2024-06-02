package com.example.nbastatsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
public interface APIService {
    @Headers({
            "X-RapidAPI-Key: 0a58e25b9bmsh009beca8bf674c2p143494jsne840fca5cc3b",
            "X-RapidAPI-Host: api-nba-v1.p.rapidapi.com"
    })
    @GET("games")
    Call<APIResponse<Game>> getGames(@Query("date") String date);
    @GET("/seasons")
    Call<APIResponse<String>> getSeasons();
}
