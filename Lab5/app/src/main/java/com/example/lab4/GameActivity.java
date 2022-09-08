package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab4.db.MyDbManager;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class GameActivity extends AppCompatActivity {

    Button up, down, left, right;
    GameView outputGame;
    TextView outputScore, outputLevel;
    EditName editName;
    int level = 1;
    private MyDbManager myDbManager;
    SimpleDateFormat formatter;
    Date date;
    SaveNickname saveNickname;

//    Locale locale = new Locale("ru");
//    Format format = new SimpleDateFormat("LLLL", Locale.getDefault().format(date));
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    NewLevelCallBack newLevelCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        up = findViewById(R.id.buttonUp);
        down = findViewById(R.id.buttonDown);
        left = findViewById(R.id.buttonLeft);
        right = findViewById(R.id.buttonRight);
        outputGame = findViewById(R.id.outputGame);
        outputScore = findViewById(R.id.score);
        outputLevel = findViewById(R.id.level);
        outputLevel.setText(level + " уровень");
        saveNickname = new SaveNickname();

        formatter= new SimpleDateFormat("yyyy-MM-dd 'в' HH:mm:ss z");
        date = new Date(System.currentTimeMillis());

        myDbManager = new MyDbManager(this);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (outputGame.getDirection() != GameSnake.DIR_DOWN)
                    outputGame.setDirection(GameSnake.DIR_UP);

            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (outputGame.getDirection() != GameSnake.DIR_UP)
                    outputGame.setDirection(GameSnake.DIR_DOWN);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (outputGame.getDirection() != GameSnake.DIR_RIGHT)
                    outputGame.setDirection(GameSnake.DIR_LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (outputGame.getDirection() != GameSnake.DIR_LEFT)
                    outputGame.setDirection(GameSnake.DIR_RIGHT);
            }
        });

        outputGame.CreateCallBack(new ScoreCallBack() {
            @Override
            public void onChange(int score) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        outputScore.setText("Ваш счёт: " + score);
                        //outputScore.setText("Ваш счёт: " + score);
                        if (score >= 30 && outputGame.getCurrentLevel() == 3) {
                            if (outputGame.getGAME_MODE() == 1) {
                                Toast.makeText(getApplicationContext(), "Вы проиграли и набрали " + score + " очков", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_LONG).show();
                                youLose();
                            }
                        }
                        else if (score >= 20 && outputGame.getCurrentLevel() == 2) {
                            if (outputGame.getGAME_MODE() == 1) {
                                Toast.makeText(getApplicationContext(), "Вы проиграли и набрали " + score + " очков", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_LONG).show();
                                youLose();
                            }
                            level++;
                            outputGame.setTimeMove(0.1f);
                            outputLevel.setText(level + " уровень");
                            outputGame.setCurrentLevel(level);
                            outputGame.invalidate();
                            outputGame.GameSnakeLevels();
                        }
                        else if (score >= 10 && outputGame.getCurrentLevel() == 1) {
                            if (outputGame.getGAME_MODE() == 1) {
                                Toast.makeText(getApplicationContext(), "Вы проиграли и набрали " + score + " очков", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_LONG).show();
                                youLose();
                            }
                            level++;
                            outputGame.setTimeMove(0.3f);
                            outputLevel.setText(level + " уровень");
                            outputGame.setCurrentLevel(level);
                            outputGame.invalidate();
                            outputGame.GameSnakeLevels();
                        }
                        else if (outputGame.getGAME_MODE() == 1) {
                            Toast.makeText(getApplicationContext(), "Вы проиграли и набрали " + score + " очков", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_LONG).show();
                            youLose();
                        }

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
    }

    void youLose() {
        myDbManager.insertToDb(saveNickname.getNickname(), getScore(), formatter.format(date), (int)outputGame.getGameTime());////fixme
//        String s1 = saveNickname.getNickname();
//        int s2 =  getScore();
//        String s3 = formatter.format(date);
//        int s4 = outputGame.getGameTime();
        finish();
    }

    public int getScore() {
        return outputGame.getScore();
    }

    public void setScore(int score) {
        outputGame.setScore(score);
    }

}