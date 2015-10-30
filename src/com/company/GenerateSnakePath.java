package com.company;

/**
 * Created by Jonas on 2015-10-29.
 */
public class GenerateSnakePath {

    private Map map;
    private Snake snake;
    private int maxWidth, maxHeight;

    public GenerateSnakePath(Map map){
        this.map = map;
        snake = new Snake();
        maxWidth = map.getColumns() - 1;
        maxHeight = map.getRows() - 1;
    }

    public void generate(){
        System.out.println("Max Height = " + maxHeight);
        System.out.println("Max Width = " + maxWidth);

        //while(all possible routes has been tested){
        Snake testSnake = testPath(new Snake());
        if(testSnake.getTraveled() > snake.getTraveled()){
            snake = testSnake;
        }
        //}
    }

    private Snake testPath(Snake snake) {
        Field[][] fields = map.getFields();
        if (snake.getPosX() < 0 || snake.getPosY() < 0)
            return null;
        if (snake.getPosX() == maxHeight || snake.getPosY() == maxWidth)
            return null;
        if (fields[snake.getPosX()][snake.getPosY()].getK() == 1)
            return null;
        if (snake.equals(end))
        {
            return snake;
        }
        //maze[start.row][start.col] = true;
        snake.setPrevPos(snake.getPosX(), snake.getPosY());
        Field[] nextField = new Field[3];
        nextField[0] = new Field(snake.getPosX() + 1, snake.getPosY(), fields[snake.getPosX()][snake.getPosY()].getK());
        nextField[2] = new Field(snake.getPosX(), snake.getPosY() + 1, fields[snake.getPosX()][snake.getPosY()].getK());
        nextField[1] = new Field(snake.getPosX() - 1, snake.getPosY(), fields[snake.getPosX()][snake.getPosY()].getK());
        nextField[3] = new Field(snake.getPosX(), snake.getPosY() - 1, fields[snake.getPosX()][snake.getPosY()].getK());
        int maxLength = -1;
        for (Field next : nextField)
        {
            snake = testPath(snake);
            if (snake != null && snake.getTraveled() > maxLength)
            {
                maxLength = snake.getTraveled();
                snake.move();
            }
        }
        return snake;
    }
}
