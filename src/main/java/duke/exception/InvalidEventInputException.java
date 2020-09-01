package duke.exception;

/**
 * Exception representing invalid input after event command.
 */
public class InvalidEventInputException extends DukeException {

    /**
     * Initiates exception
     */
    public InvalidEventInputException() {
        super("OOPS!!! Invalid input after event command. "
                + "(Example input: event project meeting /at 2020/12/20 0800)");
    }
}
