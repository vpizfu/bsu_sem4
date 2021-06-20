package web.sem2.lab1.exceptions;

public class JdbcConnectionException extends Exception {
    public JdbcConnectionException(String message) {
        super(message);
    }

    public JdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

