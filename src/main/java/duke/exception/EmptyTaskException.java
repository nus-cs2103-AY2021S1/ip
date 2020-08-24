package duke.exception;

import duke.task.TaskType;

/**
 * The Empty task exception.
 */
public class EmptyTaskException extends EmptyInputException {
    /**
     * Instantiates a new Empty task exception.
     *
     * @param message the message.
     */
    public EmptyTaskException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Empty task exception.
     *
     * @param field the field that is empty.
     * @param type  the type of task.
     */
    public EmptyTaskException(String field, TaskType type) {
        super(String.format("The %s of %s %s cannot be empty.",
                field,
                type.compareTo(TaskType.EVENT) == 0 ? "an" : "a",
                type.toString().toLowerCase()));
    }
}
