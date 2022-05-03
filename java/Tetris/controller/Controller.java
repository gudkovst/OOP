package controller;

import model.Cell;
import model.Config;
import model.Field;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private final FigureController figContr;
    private final FieldController fielContr;
    private final Field field;
    private int score;

    public Controller(){
        field = new Field();
        fielContr = new FieldController(field);
        figContr = new FigureController(field);
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public Cell[][] getField(){
        return field.getField();
    }

    public boolean controlFigure(){
        return figContr.getStatus();
    }

    public void createFigure(){
        figContr.createFigure();
    }

    public void moveFigureDown(){
        if (figContr.getStatus() && !figContr.moveFigureDown())
            fielContr.giveFigure(figContr);
    }

    public boolean glassOverflow(){
        return fielContr.glassOverflow();
    }

    public void deleteLines(){
        int numLines = fielContr.fullLines();
        score += numLines * numLines * Config.WIDTH;
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (!figContr.getStatus())
            return;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            figContr.turnFigure();
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            figContr.moveFigureLeft();
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (figContr.moveFigureDown())
                score++;
            else
                fielContr.giveFigure(figContr);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            figContr.moveFigureRight();
    }
}
