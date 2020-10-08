package duke.exceptions;

import static duke.utils.Messages.MESSAGE_EMPTY_TASK_DELETED;

/**
 * Thrown to indicate that the user input delete without the task.
 */
public class EmptyTaskDeletedException extends DukeException {

    /**
     * Constructs an EmptyTaskDeletedException with the relevant detail message.
     */
    public EmptyTaskDeletedException() {
        super(MESSAGE_EMPTY_TASK_DELETED);
    }
}
