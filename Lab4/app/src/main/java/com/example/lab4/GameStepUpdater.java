package com.example.lab4;

import java.util.TimerTask;

public class GameStepUpdater {

    GameActivity act;

    GameStepUpdater(GameActivity s){
        this.act = s;
    }

    @Override
    public void run() {
        act.Step();
    }

}
