package main.java.exceptions;

/**
 * Represents an invalid description exception thrown by Duke,
 * typically thrown when the description of a task is empty.
 * The message corresponds to the problem encountered and how to mitigate it.
 */
public class InvalidDescriptionException extends InvalidCommandException {

    public InvalidDescriptionException(String message) {
        super(message);
    }
}
