package duke.exception;

/**
 * handles the case where task type/command is invalid
 *
 */
public class WrongInputException extends DukeException {
    public WrongInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Input not in the correct format.";
    }
}
