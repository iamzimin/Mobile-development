package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


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

                    drawView.oldPx = 0;
                    drawView.oldPy = 0;
                    drawView.pX = 0;
                    drawView.pY = 0;
                    drawView.zoom = 1.0f;

                    drawView.outFX = fX;
                    drawView.outSX = sX;
                    drawView.outFY = fY;
                    drawView.outSY = sY;

                    //float temp = drawView.tempFirstX;

                }

                if (fX.getText().toString().isEmpty())
                {
                    String s = "Введите координату 'От x:'";
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                }
                if (sX.getText().toString().isEmpty())
                {
                    String s = "Введите координату 'До x:'";
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                }
                if (fY.getText().toString().isEmpty())
                {
                    String s = "Введите координату 'От y:'";
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                }
                if (sY.getText().toString().isEmpty())
                {
                    String s = "Введите координату 'До y:'";
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }



}






