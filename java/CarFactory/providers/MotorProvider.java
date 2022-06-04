package providers;

import factory.Motor;
import main.Config;
import storages.MotorStorage;

public class MotorProvider extends Thread implements Runnable{
    private final MotorStorage motorStorage;
    private static int id = 0;
    private static int Time = Config.TIME;

    public MotorProvider(MotorStorage storage){
        motorStorage = storage;
    }

    public static void changeTime(int newTime){
        Time = newTime;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(Time);
                motorStorage.giveMotor(new Motor(id));
                id++;
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
