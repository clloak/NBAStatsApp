package com.example.nbastatsapp;
import java.util.List;
public class Game {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String homeTeamLogo;
    private String awayTeamLogo;
    private String score;
    private List<PlayerStats> playerStatsList;

    // Getters and setters
    public String getHomeTeam() {
        return homeTeam;
    }
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }
    public void setHomeTeamLogo(String homeTeamLogo) {
        this.homeTeamLogo = homeTeamLogo;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }
    public void setAwayTeamLogo(String awayTeamLogo) {
        this.awayTeamLogo = awayTeamLogo;
    }

    public String getScore() {
        return score;
    }
    public List<PlayerStats> getPlayerStatsList() {
        return playerStatsList;
    }

    public void setPlayerStatsList(List<PlayerStats> playerStatsList) {
        this.playerStatsList = playerStatsList;
    }
}
