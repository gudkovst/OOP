package workers;

import exceptions.WorkerException;

import java.util.HashMap;
import java.util.Stack;

public class NullWorker implements Worker {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {}
}
