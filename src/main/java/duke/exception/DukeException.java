package duke.exception;

/**
 * Represents a general exception that deal with errors related to Duke.
 */
public class DukeException extends Exception{

    /**
     * Constructs a DukeException.
     *
     * @param errorMessage Error Message to show.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
