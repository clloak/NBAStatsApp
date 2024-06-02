package com.example.nbastatsapp;

public class PlayerStats {
    private String playerName;
    private int minutes;
    private int points;
    private int rebounds;
    private int assists;
    private int fieldGoals;
    private int threePointers;
    private int plusMinus;

    // Getters and setters
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
}
