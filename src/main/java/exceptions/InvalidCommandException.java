package main.java.exceptions;

/**
 * Represents an invalid command exception thrown by Duke,
 * usually thrown when the command provided by the user is faulty.
 * The message corresponds to the problem encountered and how to mitigate it.
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message) {
        super(message);
    }
}
