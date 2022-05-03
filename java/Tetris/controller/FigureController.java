package controller;

import model.Config;
import model.Coordinates;
import model.Field;
import model.Figure;

import java.awt.*;

public class FigureController {
    private final Field field;
    private Coordinates[] control;
    private Color color;
    private boolean status;

    public FigureController(Field field){
        this.field = field;
        control = null;
    }

    public Coordinates[] getCoords(){
        return control;
    }

    public boolean getStatus(){
        return status;
    }

    public void createFigure(){
        Figure figure = new Figure();
        control = figure.getCoords();
        color = figure.getColor();
        status = true;
    }

    public void getControl(){
        status = false;
    }

    private boolean isCellInFigure(int x, int y){
        for (Coordinates coord: control)
            if (coord.getX() == x && coord.getY() == y)
                return true;
        return false;
    }

    private boolean canMoveFigureDown(){
        int count = 0;
        for (Coordinates coord: control){
            int x = coord.getX();
            int y = coord.getY();
            if (y + 1 >= Config.HEIGHT)
                return false;
            if (isCellInFigure(x, y + 1) || y < -1 || field.getCellColor(x, y + 1) == Config.BACKCOLOR)
                count++;
        }
        return count == Config.FIG_SIZE;
    }

    public boolean moveFigureDown(){
        if (!canMoveFigureDown())
            return false;
        for (Coordinates coord: control){
            int oldx = coord.getX();
            int oldy = coord.getY();
            coord.setY(oldy + 1);
            if (oldy >= 0 && !isCellInFigure(oldx, oldy))
                field.setCellColor(oldx, oldy, Config.BACKCOLOR);
            if (oldy + 1 >= 0)
                field.setCellColor(oldx, oldy + 1, color);
        }
        return true;
    }

    private boolean canMoveFigureLeft(){
        int count = 0;
        for (Coordinates coord: control){
            int x = coord.getX();
            int y = coord.getY();
            if (x < 1)
                return false;
            if (isCellInFigure(x - 1, y)  || y < 0 || field.getCellColor(x - 1, y) == Config.BACKCOLOR)
                count++;
        }
        return count == Config.FIG_SIZE;
    }

    public void moveFigureLeft(){
        if (!canMoveFigureLeft())
            return;
        for (Coordinates coord: control){
            int oldx = coord.getX();
            int oldy = coord.getY();
            coord.setX(oldx - 1);
            if (oldy >= 0 && !isCellInFigure(oldx, oldy))
                field.setCellColor(oldx, oldy, Config.BACKCOLOR);
            if (oldy >= 0)
                field.setCellColor(oldx - 1, oldy, color);
        }
    }

    private boolean canMoveFigureRight(){
        int count = 0;
        for (Coordinates coord: control){
            int x = coord.getX();
            int y = coord.getY();
            if (x + 1 >= Config.WIDTH)
                return false;
            if (isCellInFigure(x + 1, y)  || y < 0 || field.getCellColor(x + 1, y) == Config.BACKCOLOR)
                count++;
        }
        return count == Config.FIG_SIZE;
    }

    public void moveFigureRight(){
        if (!canMoveFigureRight())
            return;
        for (Coordinates coord: control){
            int oldx = coord.getX();
            int oldy = coord.getY();
            coord.setX(oldx + 1);
            if (oldy >= 0 && !isCellInFigure(oldx, oldy))
                field.setCellColor(oldx, oldy, Config.BACKCOLOR);
            if (oldy >= 0)
                field.setCellColor(oldx + 1, oldy, color);
        }
    }

    private int getMaxX(Coordinates[] coords){
        int res = -1;
        for (Coordinates coord: coords)
            if (coord.getX() > res)
                res = coord.getX();
        return res;
    }

    public int getMinX(Coordinates[] coords){
        int res = Config.WIDTH;
        for (Coordinates coord: coords)
            if (coord.getX() < res)
                res = coord.getX();
        return res;
    }

    private boolean canTurnFigure(){
        int centreX = (getMaxX(control) + getMinX(control)) / 2;
        int centreY = -1;
        for (Coordinates coord: control)
            if (coord.getX() == centreX && coord.getY() > centreY)
                centreY = coord.getY();
        for (Coordinates coord: control) {
            int oldX = coord.getX();
            int oldY = coord.getY();
            int newX = centreY - oldY + centreX;
            int newY = centreX - oldX + centreY;
            if (newY >= Config.HEIGHT || newX < 0 || newX >= Config.WIDTH)
                return false;
            if (newY >= 0 && !isCellInFigure(newX, newY) && field.getCellColor(newX, newY) != Config.BACKCOLOR)
                return false;
        }
        return true;
    }

    public void turnFigure(){
        if (!canTurnFigure())
            return;
        int centreX = (getMaxX(control) + getMinX(control)) / 2;
        int centreY = -1;
        for (Coordinates coord: control)
            if (coord.getX() == centreX && coord.getY() > centreY)
                centreY = coord.getY();
        for (Coordinates coord: control){
            int oldX = coord.getX();
            int oldY = coord.getY();
            int newX = centreY - oldY + centreX;
            int newY = centreX - oldX + centreY;
            coord.setX(newX);
            coord.setY(newY);
            if (oldY >= 0 && !isCellInFigure(oldX, oldY))
                field.setCellColor(oldX, oldY, Config.BACKCOLOR);
            if (newY >= 0)
                field.setCellColor(newX, newY, color);
        }
    }
}
