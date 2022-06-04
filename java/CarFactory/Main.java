package main;

import controller.Controller;
import storages.AccessoryStorage;
import storages.AutoStorage;
import storages.BodyStorage;
import storages.MotorStorage;
import threadPool.ThreadsPool;
import view.View;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        AccessoryStorage accessoryStorage = new AccessoryStorage();
        AutoStorage autoStorage = new AutoStorage();
        MotorStorage motorStorage = new MotorStorage();
        BodyStorage bodyStorage = new BodyStorage();
        Logger logger = new Logger();
        ThreadsPool threadsPool = new ThreadsPool(autoStorage, accessoryStorage, bodyStorage, motorStorage, logger);
        Controller controller = new Controller(accessoryStorage, autoStorage, bodyStorage, motorStorage);
        View view = new View(controller);
        javax.swing.SwingUtilities.invokeLater(view);


        threadsPool.interruptThreads();
    }
}
