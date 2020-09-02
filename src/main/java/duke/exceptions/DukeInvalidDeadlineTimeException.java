package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the deadline time input is invalid.
 */
public class DukeInvalidDeadlineTimeException extends DukeInvalidTaskTimeException {

    @Override
    public String toString() {
        return String.format("%s\n%s", Message.ERROR_DEADLINE_TIME, Message.ERROR_TIME_FORMATTING);
    }

}
