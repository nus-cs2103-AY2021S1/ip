package duke;

/**
 * Exception class unique to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException class.
     *
     * @param errorMessage Error message that will be shown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}