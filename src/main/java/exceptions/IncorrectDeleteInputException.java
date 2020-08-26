package exceptions;

public class IncorrectDeleteInputException extends DukeException{
    public IncorrectDeleteInputException(int listSize) {
        super("Input for delete command is invalid! Input a number between 1 and " + listSize + ".");
    }
}
