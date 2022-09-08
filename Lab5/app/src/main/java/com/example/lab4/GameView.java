package com.example.lab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawThread drawThread;
    GameDraw gameDraw;
    float deltaTime = 0;
    float timeMove = 0.5f;
    float timer = 0;


    float gameTime = 0f;



    int GAME_MODE = 0;

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

                    if (GAME_MODE == 0) {
                        timer += deltaTime;

                            gameDraw.drawMap(canvas);
                            gameDraw.drawSnake(canvas);
                            if (timer > timeMove) {
                                if (gameDraw.getGameSnake().nextMove() == false)
                                    GAME_MODE = 1;
                                timer = 0;
                            }
                            gameTime += deltaTime;
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

//    void CreateCallBackLevel(NewLevelCallBack newLevelCallBack) {
//        ((GameActivity)getContext()).CreateCallBackLevel(newLevelCallBack);
//    }
//    void CreateCallBackLevel(NewLevelCallBack newLevelCallBack) {
//        (gameDraw.gameSnake).CreateCallBackLevel(newLevelCallBack);
//    }

    public int getCurrentLevel() {
        return gameDraw.gameSnake.currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        gameDraw.setCurrentLevel(currentLevel);
    }

    public int getGAME_MODE() {
        return GAME_MODE;
    }

    public void GameSnakeLevels() {
        gameDraw.GameSnakeLevels();
    }

    public void setTimeMove(float timeMove) {
        this.timeMove = timeMove;
    }

    public float getGameTime() {
        return gameTime;
    }

}

