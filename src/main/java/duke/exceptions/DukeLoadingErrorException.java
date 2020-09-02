package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which a loading error occurs.
 */
public class DukeLoadingErrorException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_LOADING_ERROR;
    }

}
