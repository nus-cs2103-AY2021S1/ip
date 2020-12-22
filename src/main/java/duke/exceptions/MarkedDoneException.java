package duke.exceptions;

/**
 * Represents an exception thrown when a done task is called to be marked done again.
 */
public class MarkedDoneException extends DukeException {
    public MarkedDoneException() {
        super("Oh no, you have already marked this task as done!");
    }
}
