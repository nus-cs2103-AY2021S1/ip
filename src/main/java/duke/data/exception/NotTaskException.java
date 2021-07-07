package duke.data.exception;

public class NotTaskException extends DukeException {
    /**
     * Constructor for Not Task Exception.
     */
    public NotTaskException() {
        super("Task type does not exist");
    }
}
