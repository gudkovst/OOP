package model;

import java.awt.*;

public class Figure {
    private final Coordinates[] coords;
    private final Color color;

    public Figure(){
        FigureType type = FigureType.randomFigure();
        color = type.getColor();
        coords = type.getCoords();
    }

    public Color getColor() {
        return color;
    }

    public Coordinates[] getCoords() {
        return coords;
    }

}
