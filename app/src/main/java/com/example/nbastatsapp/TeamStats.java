package com.example.nbastatsapp;

public class TeamStats {
    private String playerName;
    private int gamesPlayed;
    private int points;
    private int rebounds;
    private int assists;
    private int minutes;
    private int steals;
    private int blocks;
    private int turnovers;
    private int personalFouls;
    private double assistToTurnoverRatio;

    // Constructor
    public TeamStats(String playerName, int gamesPlayed, int points, int rebounds, int assists, int minutes, int steals, int blocks, int turnovers, int personalFouls, double assistToTurnoverRatio) {
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.minutes = minutes;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personalFouls = personalFouls;
        this.assistToTurnoverRatio = assistToTurnoverRatio;
    }

    // Getters
    public String getPlayerName() {
        return playerName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }
    public int getMinutes() {
        return minutes;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public double getAssistToTurnoverRatio() {
        return assistToTurnoverRatio;
    }
}
