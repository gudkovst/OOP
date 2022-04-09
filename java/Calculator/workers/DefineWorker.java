package workers;

import exceptions.WorkerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DefineWorker implements Worker{
    ArrayList<String> args;
    public DefineWorker(ArrayList<String> newArgs){
        args = newArgs;
    }

    public void execute(Stack<Double> stack, HashMap<String, Double> varBase) throws WorkerException {
        if (args.size() != 2)
            throw new WorkerException(this.getClass().getName(), "Invalid number of parameters:" + args.size());
        String name = args.get(0);
        String strValue = args.get(1);
        Double fValue;
        try {
            fValue = Double.parseDouble(strValue);
        }
        catch (NumberFormatException nfe){
            if (varBase.containsKey(strValue))
                fValue = varBase.get(strValue);
            else
                throw new WorkerException(this.getClass().getName(), "Wrong parameter entry");
        }
        varBase.put(name, fValue);
    }
}
