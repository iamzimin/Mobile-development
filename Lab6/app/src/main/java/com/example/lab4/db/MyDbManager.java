package com.example.lab4.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.lab4.UpdateListStatistics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDbManager {
    private Context context;
    private SQLiteDatabase db;

    Query query1;
    Query query2;
    Query query3;



    public int sortType = 1;

    private DatabaseReference databaseReference;
    private String USER_KEY = "Statistics";

    UpdateListStatistics updateListStatistics;

    public void registerCallBack(UpdateListStatistics updateListStatistics) {
        this.updateListStatistics = updateListStatistics;
    }

    public MyDbManager(Context context) {
        this.context = context;

        databaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY);
        query1 = databaseReference;
        query2 = databaseReference.orderByChild("score");
        query3 = databaseReference.orderByChild("deltaTime");

        getDataFromDb(1);
    }



    public void getDataFromDb(int i) {
        List<RowModel> tempList = new ArrayList<RowModel>();
        sortType = i;

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (sortType == 1) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        RowModel rowModel = ds.getValue(RowModel.class);

                        if (rowModel != null) {
                            String nickname = rowModel.nickname;
                            int score = rowModel.score;
                            String time = rowModel.time;
                            int deltaTime = rowModel.deltaTime;

                            tempList.add(new RowModel(nickname, score, time, deltaTime));
                        }
                    }
                    if (updateListStatistics != null) {
                        updateListStatistics.onChange(tempList);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (sortType == 2) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        RowModel rowModel = ds.getValue(RowModel.class);

                        if (rowModel != null) {
                            String nickname = rowModel.nickname;
                            int score = rowModel.score;
                            String time = rowModel.time;
                            int deltaTime = rowModel.deltaTime;

                            tempList.add(new RowModel(nickname, score, time, deltaTime));
                        }
                    }
                    if (updateListStatistics != null) {
                        Collections.reverse(tempList);
                        updateListStatistics.onChange(tempList);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (sortType == 3) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        RowModel rowModel = ds.getValue(RowModel.class);

                        if (rowModel != null) {
                            String nickname = rowModel.nickname;
                            int score = rowModel.score;
                            String time = rowModel.time;
                            int deltaTime = rowModel.deltaTime;

                            tempList.add(new RowModel(nickname, score, time, deltaTime));
                        }
                    }
                    if (updateListStatistics != null) {
                        Collections.reverse(tempList);
                        updateListStatistics.onChange(tempList);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
