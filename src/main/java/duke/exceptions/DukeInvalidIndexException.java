package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the list number input is invalid.
 */
public class DukeInvalidIndexException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_INVALID_INDEX;
    }

}
