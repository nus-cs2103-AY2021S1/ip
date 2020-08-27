package main.java.exceptions;

/**
 * Represents an invalid time exception thrown by Duke,
 * typically thrown when the time provided for a task is empty.
 * The message corresponds to the problem encountered and how to mitigate it.
 */
public class InvalidTimeException extends InvalidCommandException {

    public InvalidTimeException(String message) {
        super(message);
    }
}
