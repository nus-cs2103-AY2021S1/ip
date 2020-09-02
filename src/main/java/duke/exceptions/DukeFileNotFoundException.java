package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which a file cannot be loaded.
 */
public class DukeFileNotFoundException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_FILE_NOT_FOUND;
    }

}
