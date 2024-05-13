package at.ac.fhcampuswien.fhmdb.exceptions;

public class DatabaseException extends Exception{
    public DatabaseException() {
        super("Database is not working!");
    }
    public DatabaseException(String message) {
        super(message);
    }
}
