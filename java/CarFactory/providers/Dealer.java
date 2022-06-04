package providers;

import factory.Car;
import main.Config;
import main.Logger;
import storages.AutoStorage;

import java.io.IOException;

public class Dealer extends Thread implements Runnable{
    private final AutoStorage autoStorage;
    private static Logger logger;
    private final int number;
    private static final int Time = Config.TIME;

    public Dealer(AutoStorage a, int num, Logger log) {
        number = num;
        autoStorage = a;
        logger = log;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(Time);
                Car car = autoStorage.getCar();
                logger.logging(car, number);
            } catch (InterruptedException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
