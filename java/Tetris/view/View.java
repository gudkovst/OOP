package view;

import controller.Controller;
import model.Cell;
import model.Config;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Runnable {
    private final Controller contr;

    public View(Controller controller){
        contr = controller;
        addKeyListener(contr);
    }

    public void init(){
        setSize(Config.WIDTH * Config.SIZE, Config.HEIGHT * Config.SIZE * 2);
        getContentPane().setBackground(Color.orange); // Set background color
        setDefaultCloseOperation(EXIT_ON_CLOSE); // When "(X)" clicked, process is being killed
        setTitle("TETRIS"); // Set title
        setLayout(null); // No layout. Every element will be set as I want
        setVisible(true); // Show everything
        Cell[][] field = contr.getField();
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                add(field[x][y]);
    }

    @Override
    public void run() {
        init();
    }

    public void gameOver(){
        String score = Integer.toString(contr.getScore());
        String message = "<html>GAME OVER<br/><br/>YOUR SCORE:<br/>" + score + "</html>";
        JPanel gameOverPanel = new JPanel();
        JLabel gameOverText = new JLabel(message);
        gameOverText.setFont(new Font("Green", Font.BOLD,30));
        gameOverPanel.setBounds(50, 150, 350, 500);
        gameOverPanel.setBackground(Config.BACKCOLOR);
        gameOverPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        gameOverPanel.add(gameOverText);
        gameOverPanel.setVisible(true);
        add(gameOverPanel, 1);
        revalidate();
        repaint();
    }
}
