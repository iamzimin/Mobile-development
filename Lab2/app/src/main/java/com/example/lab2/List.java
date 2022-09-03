package com.example.lab2;

import android.app.Application;
import java.util.ArrayList;
import java.util.Locale;

public class List extends Application {
    private static final ArrayList<String> list = new ArrayList<>();
    //public static final Locale localeEn = new Locale("en");
    //public static final Locale localeRu = new Locale("ru");

    public static ArrayList<String> getItems() {
        return list;
    }
}
