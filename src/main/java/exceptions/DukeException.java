package main.java.exceptions;

public class DukeException extends Exception {

    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
