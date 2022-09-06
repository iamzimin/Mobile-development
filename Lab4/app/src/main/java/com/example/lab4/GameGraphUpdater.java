package com.example.lab4;

import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;

public class GameGraphUpdater extends TimerTask {

    GameSurface surf;

    GameGraphUpdater(GameSurface surf){
        this.surf = surf;
    }

    @Override
    public void run() {
        Canvas c = surf.getHolder().lockCanvas();
        if (c!=null){
            c.drawColor(Color.BLACK);
            surf.drawSnake(c);
            surf.getHolder().unlockCanvasAndPost(c);
        }
    }
}