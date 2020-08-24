package duke.exception;

/**
 * Represents an exception when user input a wrong LocalDate format.
 */
public class InvalidTaskDateException extends DukeException{

    /**
     * Constructs an InvalidTaskDateException.
     */
    public InvalidTaskDateException() {
        super("OOPS!!! Please use the format \"dd/MM/yyyy\" to indicate the date.");
    }
}
