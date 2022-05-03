package model;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private Color color;

    public Cell(int x, int y, Color color){
        setBounds(x * Config.SIZE, y * Config.SIZE, Config.SIZE, Config.SIZE);
        setColor(color);
    }

    public void setColor(Color color){
        this.color = color;
        setBackground(color);
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));
    }

    public Color getColor() {
        return color;
    }
}
