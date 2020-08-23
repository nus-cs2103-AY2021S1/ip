package main.exception;

public class EmptyMessageException extends DukeException {
    public EmptyMessageException(String command) {
        super(String.format("     ☹ OOPS!!! The description of a %s"
                        + " cannot be empty.", command));
    }
}
