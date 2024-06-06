package com.example.nbastatsapp;

public class TeamRecord {
    private int win;
    private int loss;
    private double winPercentage;

    public TeamRecord(int win, int loss, double winPercentage) {
        this.win = win;
        this.loss = loss;
        this.winPercentage = winPercentage;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public double getWinPercentage() {
        return winPercentage;
    }
}
