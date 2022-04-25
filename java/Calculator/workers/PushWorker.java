package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PushWorker implements Worker{
    ArrayList<String> args;
    public PushWorker(ArrayList<String> newArgs){
        args = newArgs;
    }
    public PushWorker(){}

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (args.size() != 1)
            throw new WorkerException(this.getClass().getName(), "Invalid number of parameters");
        String argStr = args.get(0);
        Double argum;
        try { argum = Double.parseDouble(argStr);}
		catch (NumberFormatException nfe){
            if (varBase.containsKey(argStr))
                argum = varBase.get(argStr);
            else
                throw new WorkerException(this.getClass().getName(), "Wrong parameter entry");
        }
        stack.push(argum);
    }
}
