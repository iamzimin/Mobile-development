package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Timer;

public class GameActivity extends AppCompatActivity {

    Button up, down, left, right;
    GameView outputGame;
    GameSnake gameSnake;
    GameSurface gameSurface;
    Timer t;
    //private static final int FPS = 60;
    //private static final int SPEED = 30;

    int width, height;

    float SSX = 0, SSY = 0;
    float SX = 0, SY = 0;
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        up = findViewById(R.id.buttonUp);
        down = findViewById(R.id.buttonDown);
        left = findViewById(R.id.buttonLeft);
        right = findViewById(R.id.buttonRight);
        outputGame = findViewById(R.id.outputGame);
        gameSnake = new GameSnake();
        gameSurface = new GameSurface(this);
        t = new Timer();
        height = this.getWindowManager().getDefaultDisplay().getHeight();
        width = this.getWindowManager().getDefaultDisplay().getWidth();

        t.scheduleAtFixedRate(new GameGraphUpdater(gameSurface), 0, 100);
        t.scheduleAtFixedRate(new GameStepUpdater(this), 0, 500);


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameSnake.getDirection() != GameSnake.DIR_UP)
                    gameSnake.setDirection(GameSnake.DIR_UP);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameSnake.getDirection() != GameSnake.DIR_DOWN)
                    gameSnake.setDirection(GameSnake.DIR_DOWN);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameSnake.getDirection() != GameSnake.DIR_LEFT)
                    gameSnake.setDirection(GameSnake.DIR_LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gameSnake.getDirection() != GameSnake.DIR_RIGHT)
                    gameSnake.setDirection(GameSnake.DIR_RIGHT);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        // Останавливаем таймеры
        t.cancel();
        t.purge();
    }

    private int getDirection(float x, float y) {
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) {
                return gameSnake.DIR_LEFT;
            } else {
                return gameSnake.DIR_RIGHT;
            }
        } else {
            if (y > 0) {
                return gameSnake.DIR_DOWN;
            } else {
                return gameSnake.DIR_UP;
            }
        }
    }








    public void Step() {
        // Если ход не удался то закрываем текущую активити
        if (!gameSurface.mField.nextMove()) {
            GameView.GAME_MODE=1;
            this.finish();
        }
        // Если все впорядке то обновляем очки
        // в стартовой активити
        else{
            GameView.GAME_SCORE=this.gameSurface.mField.mScore;
        }
    }



//    private void startGame() {
//        final int delay = 1000 / FPS;
//        new Thread(() -> {
//            int count = 0;
//            while (!mGameView.isGameOver()) {
//                try {
//                    Thread.sleep(delay);
//                    if (count % SPEED == 0) {
//                        mGameView.ne
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

}