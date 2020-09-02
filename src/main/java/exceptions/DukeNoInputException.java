package exceptions;

/**
 * Error Type of a Invalid Description for a task, or if it is not given.
 */
public class DukeNoInputException extends DukeException {
    /**
     * Constructor class for a DukeNoInputException
     * @param badCommand the empty command or invalid string
     */
    public DukeNoInputException(String badCommand) {
        super(badCommand, 2);
    }
}
