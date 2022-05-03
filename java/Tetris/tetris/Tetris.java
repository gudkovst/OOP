package tetris;

import controller.Controller;
import model.Config;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris {

    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);

        javax.swing.SwingUtilities.invokeLater(view);
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.glassOverflow()){
                    view.gameOver();
                    ((Timer)e.getSource()).stop();
                }
                controller.deleteLines();
                if (!controller.controlFigure())
                    controller.createFigure();
                controller.moveFigureDown();
            }
        };
        new Timer(Config.SPEED, taskPerformer).start();
    }
}
