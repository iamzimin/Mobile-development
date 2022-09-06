package com.example.lab4;

import java.util.ArrayList;

public class GameSnake {

    GameField gameField;

    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;

    public int score = 0;
    public int isGrowing = 0;
    private ArrayList<pos> snake = new ArrayList<pos>();
    int direction = GameSnake.DIR_RIGHT;

    public class pos {
        int x;
        int y;

        pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    GameSnake() {
        gameField = new GameField();
        gameField.initMap(1);

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
                        gameField.map[snake.get(0).x][snake.get(0).y] = gameField.MAP_SPACE;
                        snake.remove(0);
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    return true;

                } else if ((nextY >= 0) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    return false;

                } else if ((nextY >= 0) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing += 1;
                    score += 10;
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
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
                    }
                    snake .add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    return false;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
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
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    return false;
                } else if ((nextX < gameField.getFieldSizeX()) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
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
                    }
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = -1;
                    return true;
                } else if ((nextX >= 0) && gameField.map[nextX][nextY] == gameField.MAP_WALL) {
                    return false;
                } else if ((nextX >= 0) && gameField.map[nextX][nextY] >= gameField.MAP_APPLE) {
                    isGrowing = isGrowing + 1;
                    score += 10;
                    gameField.map[nextX][nextY] = gameField.MAP_SPACE;
                    snake.add(new pos(nextX, nextY));
                    gameField.map[nextX][nextY] = gameField.MAP_SNAKE;
                    addFruite();
                    return true;
                } else {
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

    public void clearScore(){
        this.score=0;
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

}
