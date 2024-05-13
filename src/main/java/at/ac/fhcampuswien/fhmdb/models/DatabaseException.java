package at.ac.fhcampuswien.fhmdb.models;

import java.io.IOException;

public class DatabaseException extends IOException {
    public DatabaseException(){}
    public DatabaseException(String message){
        super(message);
    }
    public DatabaseException(Throwable cause){
        super(cause);
    }
    public DatabaseException(String message, Throwable cause){
        super(message,cause);
    }
}
