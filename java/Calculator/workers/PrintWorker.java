package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PrintWorker implements Worker{
    ArrayList<String> args;
    public PrintWorker(ArrayList<String> newArgs){
        args = newArgs;
    }
    public PrintWorker(){}

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (stack.isEmpty())
            throw new WorkerException(this.getClass().getName(), "Few numbers for the operation");
        System.out.println(stack.peek());
    }
}
