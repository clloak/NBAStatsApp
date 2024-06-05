package com.example.nbastatsapp;

import java.util.List; // Add this import statement

public class RecyclerView {
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private GameAdapter gameAdapter;

    public RecyclerView(androidx.recyclerview.widget.RecyclerView recyclerView, List<Game> gameList) {
        this.recyclerView = recyclerView;
        this.gameAdapter = new GameAdapter(gameList);
        recyclerView.setAdapter(gameAdapter);
    }
    // Add other RecyclerView setup methods as needed
    //Change 1
}
