package workers;

import exceptions.ParserException;
import parsers.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Factory {
    private final Properties properties;

    public Factory() throws IOException {
        try (InputStream in = this.getClass().getResourceAsStream("workers.properties")) {
            this.properties = new Properties();
            properties.load(in);
        }
    }

    public Worker createWorker(Command command) throws ParserException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String nameCommand = this.properties.getProperty(command.getType());
        Class<?> nameWorker = Class.forName(nameCommand);
        Worker worker =  (Worker) nameWorker.newInstance();
        worker.setArgs(command.getArgs());
        return worker;
    }
}
