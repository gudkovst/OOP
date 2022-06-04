package main;

import factory.Car;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private final File logFile;

    public Logger() throws IOException {
        logFile = new File(Config.LogFile);
        if (!logFile.exists())
            logFile.createNewFile();
    }

    public void logging(Car car, int number) throws IOException {
        if (!Config.Logging)
            return;
        FileWriter logger = new FileWriter(logFile, true);
        logger.write("Dealer "+number+": Auto "+car.getCarId()+
                    "(Body:"+car.getBodyId()+" Motor:"+car.getMotorId()+" Accessory:"+car.getAccessoryId()+")\n");
        logger.close();

    }
}
