package at.ac.fhcampuswien.fhmdb.models;

import java.io.IOException;

public class MovieAPIException extends IOException {

    public MovieAPIException(){}
    public MovieAPIException(String message){
        super(message);
    }
    public MovieAPIException(Throwable cause){
        super(cause);
    }
    public MovieAPIException(String message, Throwable cause){
        super(message,cause);
    }
}
