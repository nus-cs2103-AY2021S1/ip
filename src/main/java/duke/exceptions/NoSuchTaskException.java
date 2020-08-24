package duke.exceptions;

/** Thrown to indicate that the task input by the user does not exist. */
public class NoSuchTaskException extends DukeException {

    /** Constructs a NoSuchTaskException with the relevant detail message. */
    public NoSuchTaskException() {
       super("OOPS! No such task exists!");
    }
}
