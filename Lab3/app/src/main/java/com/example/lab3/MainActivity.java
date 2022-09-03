package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.Canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    //y = 1 - cos x

    Button enter;
    EditText fX, fY, sX, sY;
    float firstX, firstY, secondX, secondY;
    boolean click = false;
    ListView listView;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        fX = findViewById(R.id.fX);
        fY = findViewById(R.id.fY);
        sX = findViewById(R.id.sX);
        sY = findViewById(R.id.sY);
        drawView = findViewById(R.id.graphOutput);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fX.getText().toString().isEmpty() && !fY.getText().toString().isEmpty() &&
                        !sX.getText().toString().isEmpty() && !sY.getText().toString().isEmpty()) {
                    firstX = Float.parseFloat(fX.getText().toString());
                    firstY = Float.parseFloat(fY.getText().toString());
                    secondX = Float.parseFloat(sX.getText().toString());
                    secondY = Float.parseFloat(sY.getText().toString());
                    drawView.fX = (int)firstX;
                    drawView.fY = (int)firstY;
                    drawView.sX = (int)secondX;
                    drawView.sY = (int)secondY;
                    drawView.invalidate();
                    drawView.click = true;
                }

            }
        });
    }



}






