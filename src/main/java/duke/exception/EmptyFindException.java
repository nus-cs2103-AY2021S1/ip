package duke.exception;

/**
 * Thrown when there is not succeeding word after find.
 */
public class EmptyFindException extends DukeException {

    /**
     * Initializes the EmptyFindException.
     */
    public EmptyFindException() {
        super("The description of a find cannot be empty!");
    }
}
