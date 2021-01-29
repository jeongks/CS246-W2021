package teach01;

public class WeakPasswordException extends Exception{

    public WeakPasswordException(String message){
        super(message);
    }
    public WeakPasswordException(Throwable throwable){
        super(throwable);
    }
    public WeakPasswordException(String message, Throwable throwable){
        super(message,throwable);
    }
}
