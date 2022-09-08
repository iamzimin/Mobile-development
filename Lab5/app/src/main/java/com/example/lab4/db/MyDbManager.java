package com.example.lab4.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDb(String nickname, int score, String time, int deltaTime) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.NICKNAME, nickname);
        cv.put(MyConstants.SCORE, score);
        cv.put(MyConstants.TIME, time);
        cv.put(MyConstants.DELTA_TIME, deltaTime);

        db.insert(MyConstants.TABLE_NAME, null, cv);

        db.close();
    }

    public List<RowModel> getFromDb(String orderBy) {
        List<RowModel> tempList = new ArrayList<RowModel>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null,
                null, null, null, orderBy);

        if (orderBy != null) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String nickname = cursor.getString(cursor.getColumnIndex(MyConstants.NICKNAME));
                    @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(MyConstants.SCORE));
                    @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(MyConstants.TIME));
                    @SuppressLint("Range") int deltaTime = cursor.getInt(cursor.getColumnIndex(MyConstants.DELTA_TIME));
                    tempList.add(new RowModel(nickname, score, time, deltaTime));
                }
                Collections.reverse(tempList);
            }
        }
        else {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String nickname = cursor.getString(cursor.getColumnIndex(MyConstants.NICKNAME));
                    @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(MyConstants.SCORE));
                    @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(MyConstants.TIME));
                    @SuppressLint("Range") int deltaTime = cursor.getInt(cursor.getColumnIndex(MyConstants.DELTA_TIME));
                    tempList.add(new RowModel(nickname, score, time, deltaTime));
                }
            }
        }

        cursor.close();
        return tempList;
    }
    public void closeDb() {
        myDbHelper.close();
    }

}
