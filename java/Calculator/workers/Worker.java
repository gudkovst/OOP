package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public interface Worker {
    void setArgs(ArrayList<String> args);
    void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException;
}
