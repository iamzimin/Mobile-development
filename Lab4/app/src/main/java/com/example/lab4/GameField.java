package com.example.lab4;

public class GameField {

    private final int fieldSizeX = 10;
    private final int fieldSizeY = 15;

    public static final int MAP_SPACE = 0;
    public static final int MAP_WALL = 1;
    public static final int MAP_SNAKE = -1;
    public static final int MAP_APPLE = 2;

    public int map[][] = new int[fieldSizeX][fieldSizeY];

    void initMap(int levelNumber) { // для добовления уровней добавить в агрументы номер уровня
        if (levelNumber == 1) {
            for (int i = 0; i < fieldSizeX; i++)
                for (int j = 0; j < fieldSizeY; j++) {

                    if (i == 0 || i == fieldSizeX - 1)
                        map[i][j] = MAP_WALL;
                    else if (j == 0 || j == fieldSizeY - 1)
                        map[i][j] = MAP_WALL;
                    else
                        map[i][j] = MAP_SPACE;

                }
        }
        if (levelNumber == 2) {
            for (int i = 0; i < fieldSizeX; i++)
                for (int j = 0; j < fieldSizeY; j++) {

                    if (i == 0 || i == fieldSizeX - 1)
                        map[i][j] = MAP_WALL;
                    else if (j == 0 || j == fieldSizeY - 1)
                        map[i][j] = MAP_WALL;
                    else
                        map[i][j] = MAP_SPACE;
                    map[6][6] = MAP_WALL;
                }

        }
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    public int[][] getMap() {
        return map;
    }


}
