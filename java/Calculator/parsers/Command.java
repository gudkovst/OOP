package parsers;

import java.util.ArrayList;

public class Command {
    private String type;
    private ArrayList<String> args;

    public Command(){
        this.type = null;
        this.args = null;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setArgs(ArrayList<String> args){
        this.args = args;
    }

    public String getType(){
        return this.type;
    }

    public ArrayList<String> getArgs(){
        return this.args;
    }
}
