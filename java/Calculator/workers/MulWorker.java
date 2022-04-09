package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MulWorker implements Worker{
    ArrayList<String> args;
    public MulWorker(ArrayList<String> newArgs){
        args = newArgs;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (stack.size() < 2)
            throw new WorkerException(this.getClass().getName(), "Few numbers for the operation");
        Double first = stack.pop();
        Double second = stack.pop();
        stack.push(first * second);
    }
}
