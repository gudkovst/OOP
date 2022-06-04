package view;

import controller.Controller;
import main.Config;
import providers.BodyProvider;
import providers.MotorProvider;
import providers.Supplier;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class View extends JFrame implements Runnable {

    private final Controller controller;
    private final JPanel[] storagePanel;
    private final JPanel[] sliders;
    private final JPanel[] titles;


    public View(Controller ctrl){
        controller = ctrl;
        storagePanel = new JPanel[5];
        sliders = new JPanel[3];
        titles = new JPanel[4];
    }

    private void init(){
        setSize(Config.WIDTH * Config.SIZE, Config.HEIGHT * Config.SIZE * 2);
        getContentPane().setBackground(Color.white); // Set background color
        setDefaultCloseOperation(EXIT_ON_CLOSE); // When "(X)" clicked, process is being killed
        setTitle("CarFactory"); // Set title
        GridLayout layout = new GridLayout(6, 2);
        setLayout(layout);
        setVisible(true); // Show everything
    }

    private void initPanels(){
        for (int i = 0; i < 5; i++)
            storagePanel[i] = new JPanel();

        String accessorySize = Integer.toString(controller.getAccessoryStorageSize());
        JLabel accessoryLabel = new JLabel(accessorySize);
        controller.getAccessoryStorage().setLabel(accessoryLabel);
        storagePanel[0].add(accessoryLabel);

        String bodySize = Integer.toString(controller.getBodyStorageSize());
        JLabel bodyLabel = new JLabel(bodySize);
        controller.getBodyStorage().setLabel(bodyLabel);
        storagePanel[1].add(bodyLabel);

        String motorSize = Integer.toString(controller.getMotorStorageSize());
        JLabel motorLabel = new JLabel(motorSize);
        controller.getMotorStorage().setLabel(motorLabel);
        storagePanel[2].add(motorLabel);

        String autoSize = Integer.toString(controller.getAutoStorageSize());
        JLabel autoLabel = new JLabel(autoSize);
        controller.getAutoStorage().setStorLabel(autoLabel);
        storagePanel[3].add(autoLabel);

        String countCar = Integer.toString(controller.getCountCars());
        JLabel countCarLabel = new JLabel(countCar);
        controller.getAutoStorage().setSoldLabel(countCarLabel);
        storagePanel[4].add(countCarLabel);
    }

    private void addAll(){
        for (int i = 0; i < 2; i++){
            add(titles[2*i]);
            add(titles[2*i + 1]);
            add(storagePanel[2*i]);
            add(storagePanel[2*i + 1]);
            add(sliders[2*i]);
            if (i == 0) add(sliders[1]);
            else add(storagePanel[4]);
        }
    }

    private JSlider createSlider(){
        JSlider slider = new JSlider(0, 2*Config.TIME, Config.TIME);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private void initSliders(){
        for (int i = 0; i < 3; i++)
            sliders[i] = new JPanel();
        JSlider bodyProviderSlider = createSlider();
        bodyProviderSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()){
                    int time = source.getValue();
                    BodyProvider.changeTime(time);
                }
            }
        });
        sliders[1].add(bodyProviderSlider);

        JSlider motorProviderSlider = createSlider();
        motorProviderSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()){
                    int time = source.getValue();
                    MotorProvider.changeTime(time);
                }
            }
        });
        sliders[2].add(motorProviderSlider);

        JSlider suppliersSlider = createSlider();
        suppliersSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()){
                    int time = source.getValue();
                    Supplier.changeTime(time);
                }
            }
        });
        sliders[0].add(suppliersSlider);
    }

    @Override
    public void run() {
        init();
        initPanels();
        initSliders();
        initTitles();
        addAll();
    }

    private void initTitles(){
        for (int i = 0; i < 4; i++)
            titles[i] = new JPanel();
        titles[0].add(new JLabel("Accessory"));
        titles[1].add(new JLabel("Body"));
        titles[2].add(new JLabel("Motor"));
        titles[3].add(new JLabel("Car"));
    }
}
