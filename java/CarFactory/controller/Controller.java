package controller;

import storages.AccessoryStorage;
import storages.AutoStorage;
import storages.BodyStorage;
import storages.MotorStorage;

public class Controller {
    private final AccessoryStorage accessoryStorage;
    private final AutoStorage autoStorage;
    private final BodyStorage bodyStorage;
    private final MotorStorage motorStorage;

    public Controller(AccessoryStorage ac, AutoStorage au, BodyStorage b, MotorStorage m){
        accessoryStorage = ac;
        autoStorage = au;
        bodyStorage = b;
        motorStorage = m;
    }

    public int getAccessoryStorageSize(){
        return accessoryStorage.getSize();
    }

    public int getAutoStorageSize(){
        return autoStorage.getSize();
    }

    public int getCountCars(){
        return autoStorage.getCountSoldCar();
    }

    public int getBodyStorageSize(){
        return bodyStorage.getSize();
    }

    public int getMotorStorageSize(){
        return motorStorage.getSize();
    }

    public AccessoryStorage getAccessoryStorage(){
        return accessoryStorage;
    }

    public AutoStorage getAutoStorage(){
        return autoStorage;
    }

    public BodyStorage getBodyStorage(){
        return bodyStorage;
    }

    public MotorStorage getMotorStorage(){
        return motorStorage;
    }
}
