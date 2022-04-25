package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class NullWorker implements Worker {
    public NullWorker(){}
    public void setArgs(ArrayList<String> args){}
    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {}
}
