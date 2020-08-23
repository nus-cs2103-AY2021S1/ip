package duke.exception;

public class NoTaskException extends DukeException {
    public NoTaskException() {
        super("You haven't add any task!");
    }
}
