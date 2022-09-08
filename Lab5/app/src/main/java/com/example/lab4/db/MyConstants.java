package com.example.lab4.db;

public class MyConstants {
    public static final String TABLE_NAME = "Statistics";
    public static final String _ID = "_id";
    public static final String NICKNAME = "nickname";
    public static final String TIME = "time";
    public static final String DELTA_TIME = "delta_time";
    public static final String SCORE = "score";
    public static final String DB_NAME = "lab5.db";
    public static final int DB_VERSION = 7;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + NICKNAME + " TEXT," +
            SCORE + " INTEGER," + TIME + " INTEGER," + DELTA_TIME + " INTEGER)";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
