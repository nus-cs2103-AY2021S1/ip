package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when the task detail of a Task object is empty.
 */
public class EmptyTaskException extends DukeException {

    private static final String EMPTY_TASK_DESCRIPTION_MESSAGE = "The description of a %s cannot be empty.";

    /**
     * Initializes the EmptyTaskException object.
     *
     * @param taskType Task type.
     */
    public EmptyTaskException(TaskType taskType) {
        super(getEmptyTaskMessage(taskType));
    }

    private static String getEmptyTaskMessage(TaskType taskType) {
        return String.format(EMPTY_TASK_DESCRIPTION_MESSAGE, toLower(taskType));
    }

    private static String toLower(TaskType taskType) {
        return taskType.toString().toLowerCase();
    }
}
