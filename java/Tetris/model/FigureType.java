package model;

import java.awt.*;

public enum FigureType {
    I, J, L, O, S, T, Z;

    private static final FigureType[] types = values();
    private static final Color[] colors = {Color.CYAN, Color.BLUE, Color.ORANGE,
            Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.RED};

    public static FigureType randomFigure(){
        int index = (int)(Math.random() * 7);
        return types[index];
    }

    public Color getColor(){
        int index = this.ordinal();
        return colors[index];
    }

    public Coordinates[] getCoords(){
        Boolean a = true;
        Coordinates[] coords = new Coordinates[Config.FIG_SIZE];
        if (this == I)
            for (int i = 0; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(5, i - 4);
        if (this == J){
            coords[0] = new Coordinates(4, -2);
            for (int i = 1; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(i + 3, -1);
        }
        if (this == L){
            coords[0] = new Coordinates(6, -2);
            for (int i = 1; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(i + 3, -1);
        }
        if (this == O)
            for (int i = 0; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(4 + i % 2, -2 + a.compareTo(i < Config.FIG_SIZE / 2));
        if (this == S)
            for (int i = 0; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(4 + i % 2 + i / 2, -1 - i % 2);
        if (this == T)
            for (int i = 0; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(4 + i % 2 + i / 2, -2 + a.compareTo(i == 2));
        if (this == Z)
            for (int i = 0; i < Config.FIG_SIZE; i++)
                coords[i] = new Coordinates(4 + i % 2 + i / 2, -2 + a.compareTo(i < Config.FIG_SIZE / 2));
        return coords;
    }
}