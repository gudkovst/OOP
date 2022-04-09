package exceptions;

public class WorkerException extends Exception{
    private final String meserr;

    public WorkerException(String nameWorker, String error){
        meserr = "ERROR in " + nameWorker + ": " + error + "\n";
    }

    public String getMessage(){
        return meserr;
    }
}
