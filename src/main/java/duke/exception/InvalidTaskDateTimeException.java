package duke.exception;

/**
 * Represents an exception when user input a wrong LocalDateTime format.
 */
public class InvalidTaskDateTimeException extends DukeException{

    /**
     * Constructs an InvalidTaskDateTimeException.
     */
    public InvalidTaskDateTimeException() {
        super("OOPS!!! Please use the format \"dd/MM/yyyy HH:mm\" to indicate the date and time.");
    }
}
