package duke.exceptions;

import static duke.utils.Messages.MESSAGE_EMPTY_EVENT_DATE;

/**
 * Thrown to indicate that the user input an event without the date or time.
 */
public class EmptyEventDateException extends DukeException {

    /**
     * Constructs an EmptyEventDateException with the relevant detail message.
     */
    public EmptyEventDateException() {
        super(MESSAGE_EMPTY_EVENT_DATE);
    }
}
