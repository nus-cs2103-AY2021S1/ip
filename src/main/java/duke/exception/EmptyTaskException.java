package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when the task detail of a Task object is empty.
 */
public class EmptyTaskException extends DukeException {
    /**
     * Initializes the EmptyTaskException object.
     *
     * @param complexTask Complex Task.
     */
    public EmptyTaskException(TaskType complexTask) {
        super(String.format("The description of a %s cannot be empty.", complexTask.toString().toLowerCase()));
    }
}
