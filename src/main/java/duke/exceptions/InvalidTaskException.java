package duke.exceptions;

import static duke.utils.Messages.MESSAGE_INVALID_TASK;

/**
 * Thrown to indicate that the task input by the user is invalid.
 */
public class InvalidTaskException extends DukeException {

    /**
     * Constructs an InvalidTaskException with the relevant detail message.
     */
    public InvalidTaskException() {
        super(MESSAGE_INVALID_TASK);
    }
}
