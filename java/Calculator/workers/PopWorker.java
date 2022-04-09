package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PopWorker implements Worker{
    ArrayList<String> args;
    public PopWorker(ArrayList<String> newArgs){
        args = newArgs;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (stack.isEmpty())
            throw new WorkerException(this.getClass().getName(), "Few numbers for the operation");
        stack.pop();
    }
}
