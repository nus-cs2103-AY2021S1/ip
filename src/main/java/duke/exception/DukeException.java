package duke.exception;

/**
 * The base exception class for all exceptions that will be thrown when running Duke.
 *  All other exceptions such as IOException and DateTimeParseException will be wrapped
 *  by some custom exception that extends DukeException.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified message.
     *
     * @param message message of the exception
     */
    public DukeException(String message) {
        super(message);
    }
}
