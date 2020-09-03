package duke.exceptions;

import static duke.utils.Messages.MESSAGE_EMPTY_SEARCH_WORD;

/**
 * Thrown to indicate that the user input find without a search word.
 */
public class EmptySearchWordException extends DukeException {

    /**
     * Constructs an EmptySearchWordException with the relevant detail message.
     */
    public EmptySearchWordException() {
        super(MESSAGE_EMPTY_SEARCH_WORD);
    }

}
