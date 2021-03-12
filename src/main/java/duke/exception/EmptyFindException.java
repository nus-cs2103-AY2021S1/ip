package duke.exception;

import static duke.util.Keyword.EMPTY_FIND_MESSAGE;

import duke.ui.Ui;

/**
 * Thrown when there is not succeeding word after find.
 */
public class EmptyFindException extends DukeException {

    /**
     * Initializes the EmptyFindException.
     */
    public EmptyFindException() {
        super(Ui.stringFormatter(EMPTY_FIND_MESSAGE));
    }
}
