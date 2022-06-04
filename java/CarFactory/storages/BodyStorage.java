package storages;

import factory.Body;
import main.Config;

import javax.swing.*;
import java.util.ArrayList;

public class BodyStorage {
    private final ArrayList<Body> storage;
    private JLabel label;

    public BodyStorage(){
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
        return storage.size() == Config.SizeBodyStorage;
    }

    public synchronized Body getBody() throws InterruptedException {
        while (isEmpty())
            wait();
        Body res = storage.remove(0);
        updateLabel();
        notify();
        return res;
    }

    public synchronized void giveBody(Body body) throws InterruptedException {
        while (isFull())
            wait();
        storage.add(body);
        updateLabel();
        notify();
    }
}
