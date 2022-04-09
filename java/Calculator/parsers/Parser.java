package parsers;

import exceptions.ParserException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public Command parse(String com) throws ParserException {
        Command command = new Command();
        if (com.length() == 0 || com.charAt(0) == '#')
            return command;
        String[] commandSplit = com.split("\\s+");
        String type = commandSplit[0];
        String[] argsArray = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
        ArrayList<String> args = new ArrayList<>(Arrays.asList(argsArray));
        if (type.equals("POP"))
            command.setType(CommandType.POP);
        if (type.equals("PUSH"))
            command.setType(CommandType.PUSH);
        if (type.equals("+"))
            command.setType(CommandType.PLUS);
        if (type.equals("-"))
            command.setType(CommandType.MINUS);
        if (type.equals("*"))
            command.setType(CommandType.MUL);
        if (type.equals("/"))
            command.setType(CommandType.DIV);
        if (type.equals("SQRT"))
            command.setType(CommandType.SQRT);
        if (type.equals("PRINT"))
            command.setType(CommandType.PRINT);
        if (type.equals("DEFINE"))
            command.setType(CommandType.DEFINE);
        if (command.getType() == null)
            throw new ParserException(type);

        command.setArgs(args);
        return command;
    }
}
