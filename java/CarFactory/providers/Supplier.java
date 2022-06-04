package providers;

import factory.Accessory;
import main.Config;
import storages.AccessoryStorage;

public class Supplier extends Thread implements Runnable{
    private final AccessoryStorage accessoryStorage;
    private static Integer id = 0;
    private static int Time = Config.TIME;

    public Supplier(AccessoryStorage storage){
        accessoryStorage = storage;
    }

    public static void changeTime(int newTime){
        Time = newTime;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(Time);
                synchronized (id) {
                    accessoryStorage.giveAccessory(new Accessory(id));
                    id++;
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
