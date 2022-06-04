package factory;

public class Car {
    private final int id;
    private final Motor motor;
    private final Body body;
    private final Accessory accessory;

    public Car(int id, Motor motor, Body body, Accessory accessory){
        this.id = id;
        this.motor = motor;
        this.body = body;
        this.accessory = accessory;
    }

    public int getCarId(){
        return id;
    }

    public int getMotorId(){
        return motor.getId();
    }

    public int getBodyId(){
        return body.getId();
    }

    public int getAccessoryId(){
        return accessory.getId();
    }
}
