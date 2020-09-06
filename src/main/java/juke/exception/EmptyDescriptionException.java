package juke.exception;

/**
 * Represents a DukeExceptions.DukeException when a user does not input a description
 * for a juke.task.Task.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
