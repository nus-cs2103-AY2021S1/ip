package duke.exception;

public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException() {
        super("OOPS. You already have this task on your list.");
    }
}
