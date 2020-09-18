package duke.exception;

/**
 * Exception representing invalid input after delete command.
 */
public class InvalidDeleteInputException extends DukeException {

    public InvalidDeleteInputException() {
        super("OOPS!!! Invalid input after delete command. Keep index within list range.\n"
                + "(Format: delete INDEX)");
    }
}
