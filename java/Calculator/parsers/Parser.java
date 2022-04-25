package parsers;

import exceptions.ParserException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public Command parse(String com) throws ParserException {
        Command command = new Command();
        if (com.length() == 0 || com.charAt(0) == '#'){
            command.setType("COMMENT");
            return command;
        }
        String[] commandSplit = com.split("\\s+");
        String type = commandSplit[0];
        String[] argsArray = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
        ArrayList<String> args = new ArrayList<>(Arrays.asList(argsArray));
        command.setType(type);
        command.setArgs(args);
        return command;
    }
}
