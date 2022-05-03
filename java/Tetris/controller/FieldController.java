package controller;

import model.Config;
import model.Coordinates;
import model.Field;

import java.awt.*;

public class FieldController {
    private final Field field;
    private final boolean[][] control;

    public FieldController(Field field){
        this.field = field;
        control = new boolean[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                control[x][y] = false;
    }

    public boolean glassOverflow(){
        for (int x = 0; x < Config.WIDTH; x++)
            if (control[x][0])
                return true;
        return false;
    }

    private void deleteLine(int numDelLine){
        for (int y = numDelLine; y >= 0; y--)
            for (int x = 0; x < Config.WIDTH; x++){
                control[x][y] = y != 0 && control[x][y - 1];
                Color color = y == 0? Config.BACKCOLOR : field.getCellColor(x, y - 1);
                field.setCellColor(x, y, color);
            }
    }

    public int fullLines(){
        int fullLines = 0;
        for (int y = 0; y < Config.HEIGHT; y++){
            int blocks = 0;
            for (; blocks < Config.WIDTH; blocks++)
                if (!control[blocks][y])
                    break;
            if (blocks == Config.WIDTH) {
                fullLines++;
                for (int x = 0; x < Config.WIDTH; x++) {
                    control[x][y] = false;
                    field.setCellColor(x, y, Config.BACKCOLOR);
                }
                deleteLine(y);
                y--;
            }
        }
        return fullLines;
    }

    public void giveFigure(FigureController fc){
        Coordinates[] figureCoords = fc.getCoords();
        for (Coordinates coord: figureCoords){
            int x = coord.getX();
            int y = coord.getY();
            if (0 <= x && x <= Config.WIDTH && 0 <= y && y <= Config.HEIGHT)
                control[x][y] = true;
        }
        fc.getControl();
    }
}
