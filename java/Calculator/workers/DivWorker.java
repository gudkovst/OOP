package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DivWorker implements Worker{
    ArrayList<String> args;
    public DivWorker(ArrayList<String> newArgs){
        args = newArgs;
    }
    public DivWorker(){}

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (stack.size() < 2)
            throw new WorkerException(this.getClass().getName(), "Few numbers for the operation");
        Double first = stack.pop();
        Double second = stack.pop();
        stack.push(first / second);
    }
}
