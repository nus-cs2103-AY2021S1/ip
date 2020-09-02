package duke.exception;

import duke.messages.Message;

/**
 * Represents a Duke exception related to the tasks within Duke.
 */
public class DukeTaskException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_TASK_ERROR;
    }

}
