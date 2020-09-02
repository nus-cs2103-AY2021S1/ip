package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a Duke exception in which the user input cannot be recognised by the parser.
 */
public class DukeUnknownInputException extends DukeException {

    @Override
    public String toString() {
        return Message.ERROR_UNKNOWN_INPUT;
    }

}
