package duke.exceptions;

/**
 * Class to initiate IncorrectDoneInputException.
 * Thrown when the input to done command is not in range or not a number.
 */
public class IncorrectDoneInputException extends DukeException {
    public IncorrectDoneInputException(int listSize) {
        super("Input for done command is invalid! Input a number between 1 and " + listSize + ".");
    }
}
