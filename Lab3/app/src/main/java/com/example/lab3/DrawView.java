package com.example.lab3;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sqrt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;


public class DrawView extends View {

    int x;
    int y;
    int fX, fY, sX, sY;
    boolean click, click2, scaling;
    private ScaleGestureDetector scaleGestureDetector;
    Paint coord;
    Path path;
    private float zoom = 1f;
    float h;
    float w;
    float oldPx, oldPy;
    float px = 0, py = 0;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        coord = new Paint();
        path = new Path();


        coord.setAntiAlias(true);
        coord.setDither(true);
        coord.setStrokeJoin(Paint.Join.ROUND);
        coord.setStrokeCap(Paint.Cap.ROUND);
        coord.setStyle(Paint.Style.STROKE);

        scaleGestureDetector = new ScaleGestureDetector(context, new MyScaleGestureListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (click) {
            x = getWidth() / 2;
            y = getHeight() / 2;

            h = getWidth();
            w = getHeight();

            float height = sY - fY;
            float width = sX - fX;
            float scaleValueX = (width - width / zoom) / 2;
            float scaleValueY = (height - height / zoom) / 2;

            float tempFirstX = fX + scaleValueX;
            float tempSecondX = sX - scaleValueX;

            float tempFirstY = fY + scaleValueY;
            float tempSecondY = sY - scaleValueY;

            float tempH = tempSecondY - tempFirstY;
            float tempW = tempSecondX - tempFirstX;

            float zoomY = (h / tempH);
            float zoomX = (w / tempW);

            tempFirstX += px / zoomX;
            tempSecondX += px / zoomX;

            tempFirstY += py / zoomY;
            tempSecondY += py / zoomY;

            float verticalX = (-tempFirstX / tempW) * w;
            float horizontalY = (1 - (-tempFirstY) / tempH) * h;


            coord.setStrokeWidth(2);
            coord.setColor(Color.GRAY);

            float z = 50;
            for (float i = verticalX; i < w; i+=z) {
                canvas.drawLine(i, 0, i, h, coord); // |
            }
            for (float i = verticalX; i > 0; i-=z) {
                canvas.drawLine(i, 0, i, h, coord); // |
            }

            for (float i = horizontalY; i < w; i+=z) {
                canvas.drawLine(0, i, w, i, coord); // -
            }
            for (float i = horizontalY; i > 0; i-=z) {
                canvas.drawLine(0, i, w, i, coord); // -
            }

            coord.setStrokeWidth(5);
            coord.setColor(Color.BLACK);

            /*canvas.drawLine(0, y, getWidth(), y, coord);
            canvas.drawLine(fX, 0, fX, getHeight(), coord);*/

            canvas.drawLine(verticalX, 0, verticalX, h, coord);     // -
            canvas.drawLine(0, horizontalY, w, horizontalY, coord); // |



            path.reset();
            path.moveTo(0, -((float) Math.sin(tempFirstX + (tempSecondX - tempFirstX) * ((float) 0 / w)) + 1) * zoomY + tempSecondY * zoomY);

            coord.setColor(Color.RED);
            for (int p = 0; p < w; p += 1) {
                float x = tempFirstX + (tempSecondX - tempFirstX) * ((float) p / w);
                float y = (float) Math.sin(x) + 1;
                path.lineTo(p, -y * zoomY + tempSecondY * zoomY);
            }

            canvas.drawPath(path, coord);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();

        scaleGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (scaling == false) {
                    px -= x - oldPx;
                    py += y - oldPy;

                    oldPx = x;
                    oldPy = y;
                }
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                if (scaling == false) {
                    oldPx = x;
                    oldPy = y;
                }
                break;
            }

            case MotionEvent.ACTION_UP: {
                scaling = false;
                break;
            }
        }
        invalidate();
        return true;
    }

    private class MyScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            zoom *= scaleGestureDetector.getScaleFactor();
            scaling = true;
            return true;
        }
    }
}
