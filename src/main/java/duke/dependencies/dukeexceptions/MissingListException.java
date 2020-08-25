package duke.dependencies.dukeexceptions;

import duke.dependencies.dukeexceptions.DukeException;

/**
 * <p>This class extends DukeExceptions, and is thrown when the saved todoList cannot be found in saved
 * cache.</p>
 *
 */
public class MissingListException extends DukeException {
    /**
     * Constructs a new MissingListException with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public MissingListException(String message) {
        super(message);
    }
}
