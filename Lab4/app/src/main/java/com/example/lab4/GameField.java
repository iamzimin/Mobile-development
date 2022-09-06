package com.example.lab4;

public class GameField {

    private final int fieldSizeX = 15;
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
        else if (levelNumber == 2) {
            for (int i = 0; i < fieldSizeX; i++)
                for (int j = 0; j < fieldSizeY; j++) {

                    if (i == 0 || i == fieldSizeX - 1)
                        map[i][j] = MAP_WALL;
                    else if (j == 0 || j == fieldSizeY - 1)
                        map[i][j] = MAP_WALL;
                    else
                        map[i][j] = MAP_SPACE;
                    map[6][6] = MAP_WALL;
                    map[6][7] = MAP_WALL;

                    map[5][4] = MAP_WALL;
                    map[7][2] = MAP_WALL;
                    map[9][9] = MAP_WALL;
                    map[12][4] = MAP_WALL;
                }
        }
        else if (levelNumber == 3) {
            for (int i = 0; i < fieldSizeX; i++)
                for (int j = 0; j < fieldSizeY; j++) {

                    if (i == 0 || i == fieldSizeX - 1)
                        map[i][j] = MAP_WALL;
                    else if (j == 0 || j == fieldSizeY - 1)
                        map[i][j] = MAP_WALL;
                    else
                        map[i][j] = MAP_SPACE;
                    map[6][6] = MAP_WALL;
                    map[6][7] = MAP_WALL;
                    map[8][4] = MAP_WALL;
                    map[5][9] = MAP_WALL;
                    map[1][7] = MAP_WALL;
                    map[2][14] = MAP_WALL;
                    map[8][9] = MAP_WALL;
                    map[3][1] = MAP_WALL;
                    map[2][12] = MAP_WALL;
                    map[11][7] = MAP_WALL;
                    map[6][2] = MAP_WALL;
                    map[9][3] = MAP_WALL;
                    map[9][5] = MAP_WALL;
                    map[1][9] = MAP_WALL;
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
