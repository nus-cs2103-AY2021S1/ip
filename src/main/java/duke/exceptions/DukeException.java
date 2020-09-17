package duke.exceptions;

import duke.messages.Message;

/**
 * Represents a general exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Standard <code>toString</code>.
     *
     * @return A string representing this task object.
     */
    @Override
    public String toString() {
        return Message.ERROR;
    }

}
