package factory;

public final class Accessory extends Detail {
    public Accessory(int id){
        this.id = id;
        this.type = DetailType.accessory;
    }
}
