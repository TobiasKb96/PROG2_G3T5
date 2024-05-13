package at.ac.fhcampuswien.fhmdb.exceptions;


public class MovieAPIException extends Exception {
    public MovieAPIException() {
        super("MovieAPI is not working");
    }
    public MovieAPIException(String message) {
        super(message);
    }
}


