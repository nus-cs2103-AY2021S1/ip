package duke.exception;

public class DukeException extends Exception {
    public DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "To see all commands, type \"help\"");
    }

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
