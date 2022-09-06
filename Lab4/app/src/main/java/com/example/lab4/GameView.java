package com.example.lab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawThread drawThread;
    GameDraw gameDraw;
    float deltaTime = 0;
    float timeMove = 0.5f;
    float timer = 0;

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
        gameDraw = new GameDraw(context);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {

        private boolean running = false;
        private SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                long time = System.currentTimeMillis();
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;

                    //gameField = new GameField();
                    //gameField.initMap(1);
                    //////////////////////////////////////////
                    timer += deltaTime;
                    if (gameDraw.getCurrentLevel() == 1)

                    gameDraw.drawMap(canvas);
                    gameDraw.drawSnake(canvas);
                    if (timer > timeMove) {
                        gameDraw.getGameSnake().nextMove();
                        timer = 0;
                    }



                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                deltaTime = (System.currentTimeMillis() - time) / 1000f;
            }
        }
    }

    void setDirection(int direction) {
        gameDraw.setDirection(direction);
    }

    public int getDirection() {
        return gameDraw.getDirection();
    }


    public int getScore() {
        return gameDraw.getScore();
    }

    public void setScore(int score) {
        gameDraw.setScore(score);
    }

    GameSnake getGameSnake() {
        return gameDraw.getGameSnake();
    }

    public void CreateCallBack(ScoreCallBack cb) {
        gameDraw.CreateCallBack(cb);
    }

    public int getCurrentLevel() {
        return gameDraw.gameSnake.currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        gameDraw.gameSnake.setCurrentLevel(currentLevel);
    }

//    public Matrix getMatrix() {
//        return gameDraw.matrix;
//    }
//
//    public void setMatrix(Matrix matrix) {
//        gameDraw.matrix = matrix;
//    }
//
//    public int getSnakePosX() {
//        return gameDraw.getSnakePosX();
//    }
//
//    public int getSnakePosY() {
//        return gameDraw.getSnakePosY();
//    }

}

