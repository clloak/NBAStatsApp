package com.example.nbastatsapp;

public class Standings {
    private int teamId;
    private Team team;
    private Conference conference;
    private Division division;
    private Win win;
    private Loss loss;
    private int wins;
    private int losses;

    // Getters and setters for the above fields

    public static class Team {
        private int id;
        private String name;
        private String nickname;
        private String code;
        private String logo;

        // Getters and setters
    }

    public static class Conference {
        private String name;
        private int rank;
        private int win;
        private int loss;

        // Getters and setters
    }

    public static class Division {
        private String name;
        private int rank;
        private int win;
        private int loss;
        private String gamesBehind;

        // Getters and setters
    }

    public static class Win {
        private int home;
        private int away;
        private int total;
        private String percentage;
        private int lastTen;

        // Getters and setters
    }

    public static class Loss {
        private int home;
        private int away;
        private int total;
        private String percentage;
        private int lastTen;

        // Getters and setters
    }
    public int getWin() {
        return wins;
    }

    public int getLoss() {
        return losses;
    }
}
