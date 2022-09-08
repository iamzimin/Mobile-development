package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab4.db.MyDbManager;
import com.example.lab4.db.RowModel;

import java.util.List;

public class ListPlayersActivity extends AppCompatActivity implements UpdateListStatistics {

    Button back, upload, longestGameTime, higestScore;
    TextView textView;
    private MyDbManager myDbManager;
    private SQLiteDatabase sqLiteDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_players);

        back = findViewById(R.id.back);
        upload = findViewById(R.id.upload);
        textView = findViewById(R.id.outputStats);
        longestGameTime = findViewById(R.id.longestGameTime);
        higestScore = findViewById(R.id.higherScore);

        myDbManager = new MyDbManager(this);

        myDbManager.registerCallBack(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDbManager.getDataFromDb(1);;

            }
        });


        longestGameTime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {

                myDbManager.getDataFromDb(3);;

            }
        });


        higestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDbManager.getDataFromDb(2);

            }
        });

    }

    @Override
    public void onChange(List<RowModel> rowModels) {
        textView.setText("");
        for (RowModel rowModel : rowModels) {
            textView.append(rowModel.getNickname());
            textView.append(" ");
            textView.append(String.valueOf(rowModel.getScore()));
            textView.append(" ");
            textView.append(rowModel.getTime());
            textView.append(" ");
            textView.append(String.valueOf(rowModel.getDeltaTime()));
            textView.append("\n");
        }
    }
}