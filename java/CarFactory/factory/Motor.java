package factory;

public final class Motor extends Detail {
    public Motor(int id){
        this.id = id;
        this.type = DetailType.motor;
    }
}
