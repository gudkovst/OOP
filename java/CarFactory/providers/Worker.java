package providers;

import factory.Accessory;
import factory.Body;
import factory.Car;
import factory.Motor;
import storages.AccessoryStorage;
import storages.AutoStorage;
import storages.BodyStorage;
import storages.MotorStorage;

public class Worker extends Thread implements Runnable{
    private final AccessoryStorage accessoryStorage;
    private final BodyStorage bodyStorage;
    private final MotorStorage motorStorage;
    private final AutoStorage autoStorage;
    private static Integer id = 0;

    public Worker(AccessoryStorage a, BodyStorage b, MotorStorage m, AutoStorage auto){
        accessoryStorage = a;
        bodyStorage = b;
        motorStorage = m;
        autoStorage = auto;
    }

    @Override
    public void run() {
        while (true){
            try {
                Motor motor = motorStorage.getMotor();
                Body body = bodyStorage.getBody();
                Accessory accessory = accessoryStorage.getAccessory();
                int n;
                synchronized (id){
                    n = id;
                    id++;
                }
                autoStorage.giveCar(new Car(n, motor, body, accessory));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
