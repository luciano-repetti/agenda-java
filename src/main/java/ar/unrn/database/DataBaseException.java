package ar.unrn.database;

public class DataBaseException extends RuntimeException{

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable reason) {
        super(message, reason);
    }

    public DataBaseException(Throwable reason) {
        super(reason);
    }
}
