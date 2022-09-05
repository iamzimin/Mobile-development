package com.example.lab4;

import java.util.ArrayList;

public class SnakeGame {

    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;

    public static int mFieldX = 20;/////
    public static int mFieldY = 40;/////

    public int mScore=0;

    private int mField[][] = new int[mFieldX][mFieldY];

    private ArrayList<pos> mSnake = new ArrayList<pos>();

    int mDirection = SnakeGame.DIR_RIGHT;

    int isGrowing = 0;

    public class pos {
        int x;
        int y;

        pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    SnakeGame() {
        for (int i = 0; i < mFieldX; i++)
            for (int j = 0; j < mFieldY; j++) {
                mField[i][j] = 0;
            }
        mSnake.add(new pos(2, 2));
        mField[2][2] = -1;
        mSnake.add(new pos(2, 3));
        mField[2][3] = -1;
        mSnake.add(new pos(2, 4));
        mField[2][4] = -1;
        addFruite();
    }

    private void addFruite() {
        boolean par = false;
        while (!par) {
            int x = (int) (Math.random() * mFieldX);
            int y = (int) (Math.random() * mFieldY);
            if (mField[x][y] == 0) {
                mField[x][y] = 2;
                par = true;
            }
        }
    }

    public boolean nextMove() {
        switch (this.mDirection) {
            case DIR_UP: {
                int nextX = mSnake.get(mSnake.size() - 1).x;
                int nextY = mSnake.get(mSnake.size() - 1).y - 1;

                if ((nextY >= 0) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);
                    }
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;

                } else if ((nextY >= 0) && mField[nextX][nextY] == 1) {
                    return false;

                } else if ((nextY >= 0) && mField[nextX][nextY] > 1) {
                    isGrowing = isGrowing + 2;
                    mScore=mScore+10;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    return true;
                } else {
                    return false;
                }
            }
            case DIR_RIGHT: {
                int nextX = mSnake.get(mSnake.size() - 1).x + 1;
                int nextY = mSnake.get(mSnake.size() - 1).y;
                if ((nextX < mFieldX) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);
                    }
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] > 1) {
                    isGrowing = isGrowing + 2;
                    mScore=mScore+10;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    return true;
                } else {
                    return false;
                }
            }
            case DIR_DOWN: {
                int nextX = mSnake.get(mSnake.size() - 1).x;
                int nextY = mSnake.get(mSnake.size() - 1).y + 1;
                if ((nextX < mFieldX) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);
                    }
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] > 1) {
                    isGrowing = isGrowing + 2;
                    mScore=mScore+10;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    return true;
                } else {
                    return false;
                }
            }
            case DIR_LEFT: {
                int nextX = mSnake.get(mSnake.size() - 1).x - 1;
                int nextY = mSnake.get(mSnake.size() - 1).y;
                if ((nextX >= 0) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);
                    }
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextX >= 0) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX >= 0) && mField[nextX][nextY] > 1) {
                    isGrowing = isGrowing + 2;
                    mScore=mScore+10;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public int getDirection() {
        return mDirection;
    }

    public void clearScore(){
        this.mScore=0;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public int[][] getmField() {
        return mField;
    }

    public int getSnakeLength() {
        return  mSnake.size();
    }

    public ArrayList<pos> getmSnake() {
        return mSnake;
    }



}
