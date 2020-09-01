package duke.exception;

/**
 * Exception representing empty deadline input.
 */
public class NullDeadlineInputException extends DukeException {

    public NullDeadlineInputException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
