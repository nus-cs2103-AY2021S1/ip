package duke.exception;

/**
 * Exception representing invalid input after done command.
 */
public class InvalidDoneInputException extends DukeException {

    public InvalidDoneInputException() {
        super("OOPS!!! Invalid input after done command. Keep index within list range.\n"
                + "(Format: done INDEX)");
    }
}
