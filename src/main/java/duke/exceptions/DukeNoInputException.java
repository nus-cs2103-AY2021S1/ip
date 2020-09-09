package duke.exceptions;

/**
 * Error Type of a Invalid Description for a task, or if it is not given.
 */
public class DukeNoInputException extends DukeException {
    /**
     * Constructs class for a DukeNoInputException
     * No input is given to this command hence returning the no input exception
     */
    public DukeNoInputException() {
        super("", 2);
    }
}
