package duke;

/**
 * An exception class that handles user input exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructor
     * @param error the error message to show
     */
    public DukeException(String error) {
        super(error);
    }
}
