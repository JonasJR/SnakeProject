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

    private Snake testPath(Snake snake){
        Field start = map.getFields()[0][0];
        Field end = map.getFields()[map.getColumns() - 1][ map.getRows() - 1];
        snake = testPath(snake, start, end);
        return snake;
    }

    private Snake testPath(Snake snake, Field start, Field end) {
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
        snake.setPrevPos(snake.getPosX(), snake.getPosY());
        Field[] nextField = new Field[4];
        nextField[0] = map.getFields()[snake.getPosX() + 1][snake.getPosY()];
        nextField[2] = map.getFields()[snake.getPosX()][snake.getPosY() + 1];
        nextField[1] = map.getFields()[snake.getPosX() - 1][snake.getPosY()];
        nextField[3] = map.getFields()[snake.getPosX()][snake.getPosY() - 1];
        int maxLength = -1;
        for (Field next : nextField)
        {
            snake = testPath(snake);
            if (snake != null && snake.getTraveled() > maxLength)
            {
                maxLength = snake.getTraveled();
            }
        }
        return snake;
    }
}
