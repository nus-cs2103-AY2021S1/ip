package duke.exception;

/**
 * Exception representing event task input.
 */
public class NullEventInputException extends DukeException {

    public NullEventInputException() {
        super("OOPS!!! The description of a event cannot be empty.");
    }
}
