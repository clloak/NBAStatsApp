package com.example.nbastatsapp;

import java.util.List;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private String gameTime;
    private String gameDate;
    private List<PlayerStats> playerStatsList;

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public List<PlayerStats> getPlayerStatsList() {
        return playerStatsList;
    }
    public void setPlayerStatsList(List<PlayerStats> playerStatsList) {
        this.playerStatsList = playerStatsList;
    }
}
