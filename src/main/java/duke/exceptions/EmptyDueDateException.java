package duke.exceptions;

/** Thrown to indicate that the user input a deadline without the due date. */
public class EmptyDueDateException extends DukeException {

    /** Constructs an EmptyDueDateException with the relevant detail message. */
    public EmptyDueDateException() {
        super("OOPS! The due date of deadline cannot be empty!");
    }

}
