package model;

import java.awt.*;

public class Field {
    private final Cell[][] field;

    public Field(){
        int height = Config.HEIGHT;
        int width = Config.WIDTH;
        field = new Cell[width][height];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                field[x][y] = new Cell(x, y, Config.BACKCOLOR);
    }

    public Cell[][] getField(){
        return field;
    }

    public void setCellColor(int x, int y, Color color){
        field[x][y].setColor(color);
    }

    public Color getCellColor(int x, int y){
        return field[x][y].getColor();
    }
}
