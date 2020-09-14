package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when there is not succeeding word after find.
 */
public class EmptyFindException extends DukeException {

    private static final String EMPTY_FIND_MESSAGE = "The query word cannot be empty!";

    /**
     * Initializes the EmptyFindException.
     */
    public EmptyFindException() {
        super(Ui.stringFormatter(EMPTY_FIND_MESSAGE));
    }
}
