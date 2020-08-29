package duke.exception;

/** An exception that occurs when trying to add an existing task into the list. */
public class DuplicateTaskException extends DukeException {

    /** Constructs a DuplicateTaskException. */
    public DuplicateTaskException() {
        super("OOPS. You already have this task on your list.");
    }
}
