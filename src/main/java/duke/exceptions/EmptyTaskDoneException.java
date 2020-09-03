package duke.exceptions;

import static duke.utils.Messages.MESSAGE_EMPTY_TASK_DONE;

/**
 * Thrown to indicate that the user input done without the task.
 */
public class EmptyTaskDoneException extends DukeException {

    /**
     * Constructs the EmptyTaskDoneException with the relevant detail message.
     */
    public EmptyTaskDoneException() {
        super(MESSAGE_EMPTY_TASK_DONE);
    }

}
