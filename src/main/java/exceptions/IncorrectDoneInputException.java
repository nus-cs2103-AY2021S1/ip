package exceptions;

public class IncorrectDoneInputException extends DukeException{
    public IncorrectDoneInputException(int listSize) {
        super("Input for done command is invalid! Input a number between 1 and " + listSize + ".");
    }
}
