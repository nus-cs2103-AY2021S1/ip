package duke.exception;

/**
 * Thrown when there is not succeeding word after find.
 */
public class EmptyFindException extends DukeException {

    private static final String EMPTY_FIND_MESSAGE = "Failed to update tasks in csv file.";

    /**
     * Initializes the EmptyFindException.
     */
    public EmptyFindException() {
        super(EMPTY_FIND_MESSAGE);
    }
}
