public class IncorrectDoneInputFormat extends DukeException{
    public IncorrectDoneInputFormat(int listSize) {
        super("Input for done command is invalid! Input a number between 0 and " + listSize + ".");
    }
}
