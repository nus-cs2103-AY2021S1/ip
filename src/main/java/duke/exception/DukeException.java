package duke.exception;

public class DukeException extends Exception {
    /**
     * Initializes a DukeException object of the String input as its error message.
     *
     * @param errorMessage The error message for the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
