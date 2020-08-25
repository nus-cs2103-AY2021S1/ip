package duke.exception;

public class NotACommandException extends DukeException {
    public NotACommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
