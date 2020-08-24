package duke.exceptions;

/** Thrown to indicate that the user input find without a search word. */
public class EmptySearchWordException extends DukeException {

    /** Constructs an EmptySearchWordException with the relevant detail message. */
    public EmptySearchWordException() {
        super("OOPS! Your search word cannot be empty.");
    }

}
