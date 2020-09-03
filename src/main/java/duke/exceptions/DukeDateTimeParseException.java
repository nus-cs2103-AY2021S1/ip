package duke.exceptions;

import static duke.utils.Messages.MESSAGE_INVALID_DATE_TIME;

/**
 * Thrown to indicate that the user input an invalid date or time format.
 */
public class DukeDateTimeParseException extends DukeException {

    /**
     * Constructs a DukeDateTimeParseException with a relevant detail message.
     */
    public DukeDateTimeParseException() {
        super(MESSAGE_INVALID_DATE_TIME);
    }
}
