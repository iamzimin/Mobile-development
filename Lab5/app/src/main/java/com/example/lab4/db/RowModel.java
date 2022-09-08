package com.example.lab4.db;

public class RowModel {

    public final String nickname;
    public final int score;
    public final String time;
    public final int deltaTime;

    public RowModel(String nickname, int score,  String time, int deltaTime) {
        this.nickname = nickname;
        this.score = score;
        this.time = time;
        this.deltaTime = deltaTime;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public int getDeltaTime() {
        return deltaTime;
    }
}
