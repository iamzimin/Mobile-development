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
    SnakeGame snakeGame;
    GameSurface surf;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        up = findViewById(R.id.buttonUp);
        down = findViewById(R.id.buttonDown);
        left = findViewById(R.id.buttonLeft);
        right = findViewById(R.id.buttonRight);
        outputGame = findViewById(R.id.outputGame);


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
                snakeGame.setDirection(snakeGame.DIR_UP);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
                snakeGame.setDirection(snakeGame.DIR_DOWN);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
                snakeGame.setDirection(snakeGame.DIR_LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
                snakeGame.setDirection(snakeGame.DIR_RIGHT);
            }
        });

    }

//    class DrawView extends SurfaceView implements SurfaceHolder.Callback {
//
//        private DrawThread drawThread;
//
//        public DrawView(Context context) {
//            super(context);
//            getHolder().addCallback(this);
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        }
//
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            drawThread = new DrawThread(getHolder());
//            drawThread.setRunning(true);
//            drawThread.start();
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//            boolean retry = true;
//            drawThread.setRunning(false);
//            while (retry) {
//                try {
//                    drawThread.join();
//                    retry = false;
//                } catch (InterruptedException e) {
//                }
//            }
//        }
//
//        class DrawThread extends Thread {
//
//            private boolean running = false;
//            private SurfaceHolder surfaceHolder;
//
//            public DrawThread(SurfaceHolder surfaceHolder) {
//                this.surfaceHolder = surfaceHolder;
//            }
//
//            public void setRunning(boolean running) {
//                this.running = running;
//            }
//
//            @Override
//            public void run() {
//                Canvas canvas;
//                while (running) {
//                    canvas = null;
//                    try {
//                        canvas = surfaceHolder.lockCanvas(null);
//                        if (canvas == null)
//                            continue;
//                        canvas.drawColor(Color.GREEN);
//                    } finally {
//                        if (canvas != null) {
//                            surfaceHolder.unlockCanvasAndPost(canvas);
//                        }
//                    }
//                }
//            }
//        }
//
//    }

}