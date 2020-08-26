package duke.Exceptions;

/**
 * Creates a DukeException to handle duke related errors
 */
public class DukeException extends Exception {

    /**
     * Creates a duke exception.
     * @param error Error message for the duke error.
     */
    public DukeException(String error){
        super(error);
    }

    /**
     * Returns the error message of the exception.
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
