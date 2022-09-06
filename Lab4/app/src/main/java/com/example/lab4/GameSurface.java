package com.example.lab4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView {

    public GameSnake mField;
    public Bitmap mHead, mTale, mBody, mBg, mFruite;

    float x, y;

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public GameSurface(Context context) {
        super(context);
        mField = new GameSnake();
        mHead =     BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        mBody =     BitmapFactory.decodeResource(context.getResources(), R.drawable.body);
        mTale =     BitmapFactory.decodeResource(context.getResources(), R.drawable.tale);
        mBg =       BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        mFruite =   BitmapFactory.decodeResource(context.getResources(), R.drawable.fruite);
    }

    public void drawSnake(Canvas c) {
        int width = c.getWidth();
        int height = c.getHeight();
        int mx = width / GameSnake.mFieldX;
        int my = height / GameSnake.mFieldY;
        Bitmap head = Bitmap.createScaledBitmap(mHead, mx, my, true);
        Bitmap body = Bitmap.createScaledBitmap(mBody, mx, my, true);
        Bitmap tale = Bitmap.createScaledBitmap(mTale, mx, my, true);
        Bitmap bg = Bitmap.createScaledBitmap(mBg, mx, my, true);
        Paint paint = new Paint();

        Bitmap fruite = Bitmap.createScaledBitmap(mFruite, mx, my, true);
        for (int i = 0; i < GameSnake.mFieldX; i++) {
            for (int j = 0; j < GameSnake.mFieldY; j++) {
                c.drawBitmap(bg, mx * i, my * j, paint);
                if (mField.getmField()[i][j] > 1) {
                    c.drawBitmap(fruite, mx * i, my * j, paint);
                }
            }
        }
        paint.setAlpha(0);
        for (int i = 0; i < mField.getSnakeLength(); i++) {
            if (i == 0)
                c.drawBitmap(head, mField.getmSnake().get(i).x * mx, mField.getmSnake().get(i).y * my, new Paint());
            else
                c.drawBitmap(body, mField.getmSnake().get(i).x * mx, mField.getmSnake().get(i).y * my, new Paint());

            if (i == mField.getSnakeLength() - 1)
                c.drawBitmap(tale, mField.getmSnake().get(i).x * mx, mField.getmSnake().get(i).y * my, new Paint());

        }
    }
}