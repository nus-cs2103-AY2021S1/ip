package juke.exception;

/**
 * Represents a DukeExceptions.DukeException when a user does not input an appropriate
 * juke.command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
