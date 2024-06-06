package com.example.nbastatsapp;
import android.os.Parcel;
import android.os.Parcelable;
public class TeamStats implements Parcelable {
    private int id;
    private String playerName;
    private String teamName;
    private String name;
    private int record;
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
    private int wins;
    private int losses;
    private int plusMinus;
    private int threePointers;
    private int fieldGoals;
    private int pFouls;
    private int fgm;
    private int fga;
    private String fgp;
    private int ftm;
    private int fta;
    private String ftp;
    private int tpm;
    private int tpa;
    private String tpp;
    private int offReb;
    private int defReb;
    private int totReb;
    private int fastBreakPoints;
    private int pointsInPaint;
    private int biggestLead;
    private int secondChancePoints;
    private int pointsOffTurnovers;
    private int longestRun;
    private int games;

    protected TeamStats(Parcel in) {
        playerName = in.readString();
        gamesPlayed = in.readInt();
        points = in.readInt();
        rebounds = in.readInt();
        assists = in.readInt();
        steals = in.readInt();
        blocks = in.readInt();
        turnovers = in.readInt();
        personalFouls = in.readInt();
        assistToTurnoverRatio = in.readFloat();
    }

    public static final Creator<TeamStats> CREATOR = new Creator<TeamStats>() {
        @Override
        public TeamStats createFromParcel(Parcel in) {
            return new TeamStats(in);
        }

        @Override
        public TeamStats[] newArray(int size) {
            return new TeamStats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeInt(gamesPlayed);
        dest.writeInt(points);
        dest.writeInt(rebounds);
        dest.writeInt(assists);
        dest.writeInt(steals);
        dest.writeInt(blocks);
        dest.writeInt(turnovers);
        dest.writeInt(personalFouls);
        dest.writeDouble(assistToTurnoverRatio);
    }
    // Constructor
    public TeamStats(int id, String playerName,String name, String teamName, int record, int gamesPlayed, int points, int rebounds, int assists, int minutes, int steals, int blocks, int turnovers, int personalFouls, double assistToTurnoverRatio) {
        this.playerName = playerName;
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.record = record;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getTeamName(){
        return teamName;
    }
    public int getRecord(){
        return record;
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

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getPlusMinus() {
        return plusMinus;
    }

    public int getFieldGoals() {
        return fieldGoals;
    }

    public int getThreePointers() {
        return threePointers;
    }
    public int getGames() { return games; }
    public int getFastBreakPoints() { return fastBreakPoints; }
    public int getPointsInPaint() { return pointsInPaint; }
    public int getBiggestLead() { return biggestLead; }
    public int getSecondChancePoints() { return secondChancePoints; }
    public int getPointsOffTurnovers() { return pointsOffTurnovers; }
    public int getLongestRun() { return longestRun; }
    public int getFgm() { return fgm; }
    public int getFga() { return fga; }
    public String getFgp() { return fgp; }
    public int getFtm() { return ftm; }
    public int getFta() { return fta; }
    public String getFtp() { return ftp; }
    public int getTpm() { return tpm; }
    public int getTpa() { return tpa; }
    public String getTpp() { return tpp; }
    public int getOffReb() { return offReb; }
    public int getDefReb() { return defReb; }
    public int getTotReb() { return totReb; }
    public int getPFouls() { return pFouls; }
}
