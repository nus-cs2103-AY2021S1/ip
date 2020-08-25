package duke.dependencies.dukeexceptions;

import duke.dependencies.dukeexceptions.DukeException;

/**
 * <p>This class extends DukeExceptions, and indicates that the user has passed in a command
 * regarding a date that is invalid.</p>
 *
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructs a new InvalidDateException with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
