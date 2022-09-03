package com.example.lab3;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sqrt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;


public class DrawView extends View {

    int x;
    int y;
    int fX, fY, sX, sY;
    boolean click;
    Paint coord;
    Path path;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Paint grid = new Paint();
        coord = new Paint();
        path = new Path();


        coord.setAntiAlias(true);
        coord.setDither(true);
        coord.setStrokeJoin(Paint.Join.ROUND);
        coord.setStrokeCap(Paint.Cap.ROUND);
        coord.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (click) {
            x = getWidth() / 2;
            y = getHeight() / 2;

             /*int z = (int)(sqrt(sX) + sqrt(sY));
            for (int i = 0; i < getHeight(); i+=z) {              // ----
                canvas.drawLine(0, i, getWidth(), i, grid);
            }

            for (int i = 0; i < getWidth(); i+=z) {              // |||||
                canvas.drawLine(i, getHeight(), i, 0, grid);
        }*/

            coord.setStrokeWidth(5);

            canvas.drawLine(0, y, getWidth(), y, coord);
            canvas.drawLine(x, 0, x, getHeight(), coord);

            float zoom = 100.0f; //уменьшить чтобы отдалить
            //float scroll = 150.0f;
            path.moveTo(0, y);

            for (int p = fX; p < sX; p+=1) {
                float x = (p - getWidth() / 2.0f) / zoom; //
                float y = 1.0f - (float) Math.cos(x);
                path.lineTo(p, getHeight() / 2f - y * zoom);
            }

            canvas.drawPath(path, coord);
        }
    }

}