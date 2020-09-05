package duke.exceptions;

/**
 * Exception thrown when users provide a task number that is not mapped to any existing tasks.
 */
public class NoSuchTaskException extends DukeException {

    public NoSuchTaskException() {
        super("No such task exists",
                "OOPS! No such task exists, please try again.");
    }
}
