package com.example.nbastatsapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerStats extends AppCompatActivity {
    private String name;
    private String teamName;
    private String firstname;
    private String lastname;
    private int gamesPlayed;
    private String playerName;
    private int id;
    private int minutes;
    private int points;
    private int rebounds;
    private int assists;
    private int fieldGoals;
    private int threePointers;
    private int plusMinus;

    public PlayerStats(String name, String firstname, String lastname, String teamName, int id, int gamesPlayed, String playerName, int minutes, int points, int rebounds, int assists, int fieldGoals, int threePointers, int plusMinus){
        this.name = name;
        this.firstname = firstname;
        this.lastname = lastname;
        this.teamName = teamName;
        this.id = id;
        this.gamesPlayed = gamesPlayed;
        this.minutes = minutes;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.fieldGoals = fieldGoals;
        this.threePointers = threePointers;
        this.plusMinus = plusMinus;
    }
    // Getters and setters
    public String getName(){
        return name;
    }
    public String getTeamName(){
        return teamName;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getGamesPlayed(){
        return gamesPlayed;
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFieldGoals() {
        return fieldGoals;
    }

    public void setFieldGoals(int fieldGoals) {
        this.fieldGoals = fieldGoals;
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void setThreePointers(int threePointers) {
        this.threePointers = threePointers;
    }

    public int getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(int plusMinus) {
        this.plusMinus = plusMinus;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public int getId() {
        return id;
    }
}
