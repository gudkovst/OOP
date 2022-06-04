package storages;

import factory.Car;
import main.Config;

import javax.swing.*;
import java.util.ArrayList;

public class AutoStorage {
    private final ArrayList<Car> storage;
    private JLabel storLabel;
    private int countSoldCar;
    private JLabel soldLabel;

    public AutoStorage(){
        storage = new ArrayList<>();
        storLabel = null;
        countSoldCar = 0;
        soldLabel = null;
    }

    public void setStorLabel(JLabel jLabel){
        storLabel = jLabel;
    }

    public void setSoldLabel(JLabel jLabel){
        soldLabel = jLabel;
    }

    public synchronized int getCountSoldCar(){
        return countSoldCar;
    }

    private void updateLabel(JLabel label, String text){
        if (label != null)
            label.setText(text);
    }

    public synchronized int getSize(){
        return storage.size();
    }

    private synchronized boolean isEmpty(){
        return storage.isEmpty();
    }

    private synchronized boolean isFull(){
        return storage.size() == Config.SizeAutoStorage;
    }

    public synchronized Car getCar() throws InterruptedException {
        while (isEmpty())
                wait();

        Car res = storage.remove(0);
        countSoldCar++;
        updateLabel(storLabel, Integer.toString(getSize()));
        updateLabel(soldLabel, Integer.toString(countSoldCar));
        notify();
        return res;
    }

    public synchronized void giveCar(Car car) throws InterruptedException {
        while (isFull())
            wait();

        storage.add(car);
        updateLabel(storLabel, Integer.toString(getSize()));
        notify();
    }
}
