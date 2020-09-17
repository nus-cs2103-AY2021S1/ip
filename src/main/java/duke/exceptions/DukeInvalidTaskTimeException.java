package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the task time input is invalid.
 */
public class DukeInvalidTaskTimeException extends DukeTaskException {

    @Override
    public String toString() {
        return String.format("%s\n%s", Message.ERROR_INVALID_TASK_TIME, Message.ERROR_TIME_FORMATTING);
    }

}
