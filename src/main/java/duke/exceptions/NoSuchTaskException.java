package duke.exceptions;

import static duke.utils.Messages.MESSAGE_NO_SUCH_TASK;

/**
 * Thrown to indicate that the task input by the user does not exist.
 */
public class NoSuchTaskException extends DukeException {

    /**
     * Constructs a NoSuchTaskException with the relevant detail message.
     */
    public NoSuchTaskException() {
        super(MESSAGE_NO_SUCH_TASK);
    }
}
