package com.example.lab4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameDraw extends SurfaceView {

    GameField gameField;
    GameSnake gameSnake;
    Matrix matrix;

    public Bitmap mHead, mTale, mBody, mBg, mFruite, mRock;

    public GameDraw(Context context) {
        super(context);
        gameSnake = new GameSnake();
        gameField = gameSnake.getField();
        matrix = new Matrix();

        mHead =     BitmapFactory.decodeResource(context.getResources(), R.drawable.tale);
        mBody =     BitmapFactory.decodeResource(context.getResources(), R.drawable.body);
        mTale =     BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        mBg =       BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        mFruite =   BitmapFactory.decodeResource(context.getResources(), R.drawable.fruite);
        mRock =     BitmapFactory.decodeResource(context.getResources(), R.drawable.rock);
    }


    public void drawMap(Canvas c) {

        int width = c.getWidth();
        int height = c.getHeight();
        int mx = width / gameField.getFieldSizeX();
        int my = height / gameField.getFieldSizeY();

        Bitmap bg = Bitmap.createScaledBitmap(mBg, mx, my, true);
        Bitmap fruite = Bitmap.createScaledBitmap(mFruite, mx, my, true);
        Bitmap rock = Bitmap.createScaledBitmap(mRock, mx, my, true);

        Paint paint = new Paint();

        for (int i = 0; i < gameField.getFieldSizeX(); i++) {
            for (int j = 0; j < gameField.getFieldSizeY(); j++) {
                c.drawBitmap(bg, mx * i, my * j, paint);
                if (gameField.getMap()[i][j] == gameField.MAP_WALL) {
                    c.drawBitmap(rock, mx * i, my * j, paint);
                }
                else if (gameField.getMap()[i][j] >= gameField.MAP_APPLE) {
                    c.drawBitmap(fruite, mx * i, my * j, paint);
                }
            }
        }

    }

    public void drawSnake(Canvas c) {
        int width = c.getWidth();
        int height = c.getHeight();
        int mx = width / gameField.getFieldSizeX();
        int my = height / gameField.getFieldSizeY();
        Bitmap head = Bitmap.createScaledBitmap(mHead, mx, my, true);
        Bitmap body = Bitmap.createScaledBitmap(mBody, mx, my, true);
        Bitmap tale = Bitmap.createScaledBitmap(mTale, mx, my, true);
//        if (gameSnake.getIsTail()) {
//            matrix.postRotate(0);
//            tale = Bitmap.createBitmap(mTale, 0, 0, mx, my, matrix, true);
//
//            gameSnake.setIsTail(false);
//        } else {
//            tale = Bitmap.createScaledBitmap(mTale, mx, my, true);
//        }

        matrix = getMatrix();

        Paint paint = new Paint();
        paint.setAlpha(0);
        for (int i = 0; i < gameSnake.getSnakeLength(); i++) {
            if (i == 0)
                c.drawBitmap(head, gameSnake.getSnake().get(i).getSnakePosX() * mx,  gameSnake.getSnake().get(i).getSnakePosY() * my, new Paint());
            else
                c.drawBitmap(body,  gameSnake.getSnake().get(i).getSnakePosX() * mx,  gameSnake.getSnake().get(i).getSnakePosY() * my, new Paint());

            if (i == gameSnake.getSnakeLength() - 1)
                c.drawBitmap(tale, gameSnake.getSnake().get(i).getSnakePosX() * mx, gameSnake.getSnake().get(i).getSnakePosY() * my, new Paint());

        }
    }

    GameSnake getGameSnake() {
        return gameSnake;
    }

    void setDirection(int direction) {
        gameSnake.setDirection(direction);
    }

    public int getDirection() {
        return gameSnake.direction;
    }


    public int getScore() {
        return gameSnake.getScore();
    }

    public void setScore(int score) {
        gameSnake.setScore(score);
    }

    void CreateCallBack(ScoreCallBack cb) {
        gameSnake.CreateCallBack(cb);
    }

    public int getCurrentLevel() {
        return gameSnake.currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        gameSnake.setCurrentLevel(currentLevel);
    }

//    public Matrix getMatrix() {
//        return matrix;
//    }
//
//    public void setMatrix(Matrix matrix) {
//        this.matrix = matrix;
//    }
//
//    public int getSnakePosX() {
//        return getSnakePosX();
//    }
//
//    public int getSnakePosY() {
//        return getSnakePosY();
//    }

}
