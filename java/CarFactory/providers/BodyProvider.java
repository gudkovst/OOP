package providers;

import factory.Body;
import main.Config;
import storages.BodyStorage;

public class BodyProvider extends Thread implements Runnable{
    private final BodyStorage bodyStorage;
    private static int id = 0;
    private static int Time = Config.TIME;

    public BodyProvider(BodyStorage storage){
        bodyStorage = storage;
    }

    public static void changeTime(int newTime){
        Time = newTime;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(Time);
                bodyStorage.giveBody(new Body(id));
                id++;
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
