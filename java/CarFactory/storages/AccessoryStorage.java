package storages;

import factory.Accessory;
import main.Config;

import javax.swing.*;
import java.util.ArrayList;

public class AccessoryStorage {
    private final ArrayList<Accessory> storage;
    private JLabel label;

    public AccessoryStorage(){
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
        return storage.size() == Config.SizeAccessoryStorage;
    }

    public synchronized Accessory getAccessory() throws InterruptedException {
        while (isEmpty())
            wait();
        Accessory res = storage.remove(0);
        updateLabel();
        notify();
        return res;
    }

    public synchronized void giveAccessory(Accessory accessory) throws InterruptedException {
        while (isFull())
            wait();
        storage.add(accessory);
        updateLabel();
        notify();
    }
}
