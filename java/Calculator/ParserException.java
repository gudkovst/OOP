package exceptions;

public class ParserException extends Exception {
    private final String meserr;

    public ParserException(String nameCommand){
        meserr = "ERROR command: " + nameCommand;
    }

    public String getMessage(){
        return meserr;
    }
}
