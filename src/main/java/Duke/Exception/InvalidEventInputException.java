package Duke.Exception;

public class InvalidEventInputException extends DukeException {

    public InvalidEventInputException() {
        super("OOPS!!! Invalid input after event command. "
                + "(Example input: event project meeting /at 2020/12/20 0800)");
    }
}
