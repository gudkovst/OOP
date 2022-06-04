package storages;

import factory.Motor;
import main.Config;

import javax.swing.*;
import java.util.ArrayList;

public class MotorStorage {
    private final ArrayList<Motor> storage;
    private JLabel label;

    public MotorStorage(){
        storage = new ArrayList<>();
        label = null;
    }

    public void setLabel(JLabel jLabel){
        label = jLabel;
    }

    private void updateLabel(){
        if (label != null)
            label.setText(Integer.toString(getSize()));
    }

    public synchronized int getSize(){
        return storage.size();
    }

    private synchronized boolean isEmpty(){
        return storage.isEmpty();
    }

    private synchronized boolean isFull(){
        return storage.size() == Config.SizeMotorStorage;
    }

    public synchronized Motor getMotor() throws InterruptedException {
        while (isEmpty())
            wait();
        Motor res = storage.remove(0);
        updateLabel();
        notify();
        return res;
    }

    public synchronized void giveMotor(Motor motor) throws InterruptedException {
        while (isFull())
            wait();
        storage.add(motor);
        updateLabel();
        notify();
    }
}
