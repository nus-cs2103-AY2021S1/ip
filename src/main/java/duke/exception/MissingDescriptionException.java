package duke.exception;

import duke.task.TaskType;

/**
 * Exception thrown when the task has missing element.
 */
public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(TaskType type) {
        super("The description of " + type.toString().toLowerCase() + " cannot be empty.");
    }
}
