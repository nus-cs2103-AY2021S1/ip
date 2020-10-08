package duke.exceptions;

import static duke.utils.Messages.MESSAGE_EMPTY_DUE_DATE;

/**
 * Thrown to indicate that the user input a deadline without the due date.
 */
public class EmptyDueDateException extends DukeException {

    /**
     * Constructs an EmptyDueDateException with the relevant detail message.
     */
    public EmptyDueDateException() {
        super(MESSAGE_EMPTY_DUE_DATE);
    }

}
