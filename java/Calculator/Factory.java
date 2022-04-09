package workers;

import exceptions.ParserException;
import parsers.Command;
import parsers.CommandType;

public class Factory {
    public Worker createWorker(Command command) throws ParserException {
        if (command.getType() == null)
            return new NullWorker();
        if (command.getType() == CommandType.DEFINE)
            return new DefineWorker(command.getArgs());
        if (command.getType() == CommandType.DIV)
            return new DivWorker(command.getArgs());
        if (command.getType() == CommandType.MUL)
            return new MulWorker(command.getArgs());
        if (command.getType() == CommandType.MINUS)
            return new MinusWorker(command.getArgs());
        if (command.getType() == CommandType.PLUS)
            return new PlusWorker(command.getArgs());
        if (command.getType() == CommandType.POP)
            return new PopWorker(command.getArgs());
        if (command.getType() == CommandType.PRINT)
            return new PrintWorker(command.getArgs());
        if (command.getType() == CommandType.PUSH)
            return new PushWorker(command.getArgs());
        if (command.getType() == CommandType.SQRT)
            return new SqrtWorker(command.getArgs());
        throw new ParserException(command.getType().name());
    }
}
