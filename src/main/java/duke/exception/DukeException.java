package duke.exception;

/**
 * Exception class for Duke to handle Duke-only errors.
 */
public class DukeException extends Exception {
    protected String input;

    public DukeException(String input) {
        this.input = input;
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     * 
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what went wrong, but Duke knows something went wrong -> " + input;
    }
}
