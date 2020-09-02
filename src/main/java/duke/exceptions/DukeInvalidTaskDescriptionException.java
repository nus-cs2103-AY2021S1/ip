package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the input's task description is invalid.
 */
public class DukeInvalidTaskDescriptionException extends DukeTaskException {

    @Override
    public String toString() {
        return Message.ERROR_INVALID_TASK_DESCRIPTION;
    }

}
