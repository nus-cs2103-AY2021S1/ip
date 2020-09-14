package duke.exception;

public class WrongDueInFormatException extends DukeException {
    public WrongDueInFormatException() {
        super("Error! 'due in' command is in the wrong format.");
    }
}
