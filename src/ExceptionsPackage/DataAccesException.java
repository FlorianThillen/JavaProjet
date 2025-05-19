package ExceptionsPackage;

public class DataAccesException extends Exception {
    public DataAccesException(String message){
        super(message);
    }
    public DataAccesException(String message,Throwable cause){
        super(message,cause);
    }
}
