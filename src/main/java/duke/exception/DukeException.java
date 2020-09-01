package duke.exception;

/**
 * Represents DukeExceptions used in duke.Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException with a specified message.
     *
     * @param errorMessage Error message to be shown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
