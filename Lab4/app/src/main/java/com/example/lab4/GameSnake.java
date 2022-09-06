package com.example.lab4;

import java.util.ArrayList;

public class GameSnake implements NewLevelCallBack{

    GameField gameField;

    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;
    int direction;

    int currentLevel = 1;

//    public boolean isHead = false;
//    public boolean isBody = false;
//
//    public boolean isTail = false;

    ScoreCallBack scoreCallBack;


    public int score = 0;
    public int isGrowing = 0;
    private ArrayList<pos> snake = new ArrayList<pos>();

    @Override
    public void onChange(int level) {

    }

    public class pos {

        private int x;
        private int y;

        pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getSnakePosX() {
            return x;
        }

        public int getSnakePosY() {
            return y;
        }
    }


    GameSnake() {
        GameSnakeLevels();
    }

    public void GameSnakeLevels() {
        gameField = new GameField();
        direction = GameSnake.DIR_RIGHT;
        gameField.initMap(currentLevel);

        while (snake.size() != 0)
            snake.remove(0);


        snake.add(new pos(2, 2)); // Можно создвавать рандомно
        gameField.map[2][2] = gameField.MAP_SNAKE;
        snake.add(new pos(2, 3));
        gameField.map[2][3] = gameField.MAP_SNAKE;
        snake.add(new pos(2, 4));
        gameField.map[2][4] = gameField.MAP_SNAKE;
        addFruite();
    }

    private void addFruite() {
        boolean par = false;
        while (!par) {
            int x = (int) (Math.random() * gameField.getFieldSizeX());
            int y = (int) (Math.random() * gameField.getFieldSizeY());
            if (gameField.map[x][y] == gameField.MAP_SPACE) {
                gameField.map[x][y] = gameField.MAP_APPLE;
                par = true;
            }
        }
    }

    public boolean nextMove() {
        switch (this.direction) {
            case DIR_UP: {
                int nextX = snake.get(snake.size() - 1).x;
                int nextY = snake.get(snake.size() - 1).y - 1;

                if ((nextY >= 0) && gameField.map[nextX][nextY] == gameField.MAP_SPACE) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE; //////// хвост
                        snake.remove(0);
                        //isTail = true;
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    return true;

                } else if ((nextY >= 0) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    scoreCallBack.onChange(score);
                    return false;

                } else if ((nextY >= 0) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing += 1;
                    score += 10;
                    scoreCallBack.onChange(score);
                    gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE; //////// хвост
                    snake.remove(0);
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
                    scoreCallBack.onChange(score);
                    return false;
                }
            }
            case DIR_RIGHT: {
                int nextX = snake.get(snake.size() - 1).x + 1;
                int nextY = snake.get(snake.size() - 1).y;
                if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_SPACE) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE;
                        snake.remove(0);
                        //isTail = true;
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    scoreCallBack.onChange(score);
                    return false;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    scoreCallBack.onChange(score);
                    gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE; //////// хвост
                    snake.remove(0);
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
                    scoreCallBack.onChange(score);
                    return false;
                }
            }
            case DIR_DOWN: {
                int nextX = snake.get(snake.size() - 1).x;
                int nextY = snake.get(snake.size() - 1).y + 1;
                if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_SPACE) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        gameField.map[snake.get(0).x][snake.get(0).y] = 0;
                        snake.remove(0);
                        //isTail = true;
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    scoreCallBack.onChange(score);
                    return false;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    scoreCallBack.onChange(score);
                    gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE; //////// хвост
                    snake.remove(0);
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
                    scoreCallBack.onChange(score);
                    return false;
                }
            }
            case DIR_LEFT: {
                int nextX = snake.get(snake.size() - 1).x - 1;
                int nextY = snake.get(snake.size() - 1).y;
                if ((nextX >= 0) && gameField.map[nextX][nextY] == gameField.MAP_SPACE) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE;
                        snake.remove(0);
                        //isTail = true;
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX >= 0) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    scoreCallBack.onChange(score);
                    return false;
                } else if ((nextX >= 0) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    scoreCallBack.onChange(score);
                    gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE; //////// хвост
                    snake.remove(0);
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
                    scoreCallBack.onChange(score);
                    return false;
                }
            }
        }
        return false;
    }

    public GameField getField() {
        return gameField;
    }

    public int getDirection() {
        return direction;
    }

    void CreateCallBack(ScoreCallBack cb) {
        scoreCallBack = cb;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSnakeLength() {
        return snake.size();
    }

    public ArrayList<pos> getSnake() {
        return snake;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getFieldSizeX() {
        return gameField.getFieldSizeX();
    }

    public int getFieldSizeY() {
        return gameField.getFieldSizeX();
    }

    public int[][] getMap() {
        return gameField.getMap();
    }


//    public void setIsHead(boolean head) {
//        isHead = head;
//    }
//
//    public void setIsBody(boolean body) {
//        isBody = body;
//    }
//
//    public void setIsTail(boolean tail) {
//        isTail = tail;
//    }
//
//
//    public boolean getIsHead() {
//        return isHead;
//    }
//
//    public boolean getIsBody() {
//        return isBody;
//    }
//
//    public boolean getIsTail() {
//        return isTail;
//    }

}
