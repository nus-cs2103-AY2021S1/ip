package duke.exceptions;

/**
 * Class to initiate IncorrectDeleteInputException.
 * Thrown when the input to delete command is not in range or not a number.
 */
public class IncorrectDeleteInputException extends DukeException{
    public IncorrectDeleteInputException(int listSize) {
        super("Input for delete command is invalid! Input a number between 1 and " + listSize + ".");
    }
}
