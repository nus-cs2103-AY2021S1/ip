package duke.exceptions;

/** Thrown to indicate that the task input by the user is invalid. */
public class InvalidTaskException extends DukeException {

    /** Constructs an InvalidTaskException with the relevant detail message. */
    public InvalidTaskException() {
        super("OOPS! Invalid task found.");
    }
}
