package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SqrtWorker implements Worker{
    ArrayList<String> args;
    public SqrtWorker(ArrayList<String> newArgs){
        args = newArgs;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (stack.size() < 1)
            throw new WorkerException(this.getClass().getName(), "Few numbers for the operation");
        Double first = stack.pop();
        stack.push(Math.sqrt(first));
    }
}
