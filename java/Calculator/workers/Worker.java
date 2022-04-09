package workers;

import exceptions.WorkerException;

import java.util.HashMap;
import java.util.Stack;

public interface Worker {
    void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException;
}
