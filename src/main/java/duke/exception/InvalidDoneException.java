package duke.exception;

/**
 * Thrown when the user inputs a wrong done format.
 */
public class InvalidDoneException extends DukeException {
    /**
     * Initializes the InvalidDone object with the error message suggesting the proper format.
     */
    public InvalidDoneException() {
        super("Done format is invalid.\n    Please try again with a proper format like 'done 3'");
    }
}