package main.java.exceptions;

/**
 * Represents an invalid number exception thrown by Duke,
 * typically thrown when the number index provided is out of range.
 * The message corresponds to the problem encountered and how to mitigate it.
 */
public class InvalidNumberException extends InvalidCommandException {

    public InvalidNumberException(String message) {
        super(message);
    }
}
