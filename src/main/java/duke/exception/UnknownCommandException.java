package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry, I don't understand what you just said.");
    }
}
