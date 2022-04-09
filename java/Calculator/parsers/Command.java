package parsers;

import java.util.ArrayList;

public class Command {
    private CommandType type;
    private ArrayList<String> args;

    public Command(){
        this.type = null;
        this.args = null;
    }

    public void setType(CommandType type){
        this.type = type;
    }

    public void setArgs(ArrayList<String> args){
        this.args = args;
    }

    public CommandType getType(){
        return this.type;
    }

    public ArrayList<String> getArgs(){
        return this.args;
    }
}
