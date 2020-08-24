package duke.exception;

/**
 * Represents an exception when user input an invalid task number.
 */
public class InvalidTaskNumberException extends DukeException{

    /**
     * Constructs an InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        super("OOPS!!! Task Number is invalid.");
    }
}
