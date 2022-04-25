package calculator;

import exceptions.ParserException;
import exceptions.WorkerException;
import parsers.Command;
import parsers.MyLogger;
import parsers.Parser;
import workers.Factory;
import workers.Worker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan;
        MyLogger logger;
        if (args.length == 0) {
            scan = new Scanner(System.in);
            logger = new MyLogger("1.log");
            logger.logging("Read commands from keyboard\n");
        }
        else {
            Path path = Paths.get(args[0]);
            scan = new Scanner(path);
            if (args.length == 1)
                logger = new MyLogger("1.log");
            else
                logger = new MyLogger(args[1]);
            logger.logging("Read commands from " + args[0]);
        }
        Stack<Double> stack = new Stack<>();
        HashMap<String, Double> varBase = new HashMap<>();
        Factory factory = new Factory();
        Parser parser = new Parser();

        try{
            while(scan.hasNext()){
                Command command = parser.parse(scan.nextLine());
                Worker worker = factory.createWorker(command);
                worker.execute(stack, varBase);
                logger.logging(command);
            }
            scan.close();
        }
        catch (ParserException | WorkerException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.err.println(e.getMessage());
            logger.logging(e.getMessage());
        }
        logger.close();
    }
}

