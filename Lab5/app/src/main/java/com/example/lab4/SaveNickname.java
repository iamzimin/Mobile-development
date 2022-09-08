package com.example.lab4;

import java.util.ArrayList;

public class SaveNickname {
    private static String nickname = "";
    //public static final Locale localeEn = new Locale("en");
    //public static final Locale localeRu = new Locale("ru");

    void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static String getNickname() {
        return nickname;
    }
}
