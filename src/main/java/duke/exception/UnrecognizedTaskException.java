package duke.exception;

public class UnrecognizedTaskException extends DukeException {
    public UnrecognizedTaskException() {
        super("OOPS. Bolot does not recognize the command :( Try again.");
    }
}
