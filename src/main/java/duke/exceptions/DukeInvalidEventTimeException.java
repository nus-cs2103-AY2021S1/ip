package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the event time input is invalid.
 */
public class DukeInvalidEventTimeException extends DukeInvalidTaskTimeException {

    @Override
    public String toString() {
        return String.format("%s\n%s", Message.ERROR_EVENT_TIME, Message.ERROR_TIME_FORMATTING);
    }

}
