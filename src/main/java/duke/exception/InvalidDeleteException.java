package duke.exception;

/**
 * Thrown when the user inputs a wrong delete format.
 */
public class InvalidDeleteException extends DukeException {
    /**
     * Initializes the InvalidDeleteException object with the error message suggesting the proper format.
     */
    public InvalidDeleteException() {
        super("Delete format is invalid.\n    Please try again with a proper format like 'delete 3'");
    }
}
