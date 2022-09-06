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
    //GameSnake gameSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        up = findViewById(R.id.buttonUp);
        down = findViewById(R.id.buttonDown);
        left = findViewById(R.id.buttonLeft);
        right = findViewById(R.id.buttonRight);
        outputGame = findViewById(R.id.outputGame);
        //gameSnake = new GameSnake();

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (outputGame.getDirection() != GameSnake.DIR_DOWN)
                    outputGame.setDirection(GameSnake.DIR_UP);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (outputGame.getDirection() != GameSnake.DIR_UP)
                    outputGame.setDirection(GameSnake.DIR_DOWN);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //if (outputGame.getDirection() != GameSnake.DIR_RIGHT)
                    outputGame.setDirection(GameSnake.DIR_LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (outputGame.getDirection() != GameSnake.DIR_LEFT)
                    outputGame.setDirection(GameSnake.DIR_RIGHT);
            }
        });

    }


}