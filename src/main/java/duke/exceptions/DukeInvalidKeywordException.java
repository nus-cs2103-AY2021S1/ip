package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the keyword is empty (that is to say: invalid).
 */
public class DukeInvalidKeywordException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_INVALID_KEYWORD;
    }

}
