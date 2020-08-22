package duke.exception;

/**
 * Represents a DukeException in which the input is empty.
 */
public class DukeEmptyInputException extends DukeException{
    /**
     * Constructor.
     * @param message string representing the exception
     */
    public DukeEmptyInputException(String message) {
        super(message);
    }
}
