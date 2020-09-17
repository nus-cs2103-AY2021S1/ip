package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the date input is invalid.
 */

public class DukeInvalidScheduleInputException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s\n%s", Message.ERROR_SCHEDULE_INPUT, Message.ERROR_DATE_FORMATTING);
    }

}
