package clippy.exception;

import clippy.task.TaskType;

/**
 * Represents an exception due to missing task description in a command execution.
 */
public class EmptyDescriptionException extends ClippyException {
    /**
     * Constructs an EmptyDescriptionException with a pre-defined error message.
     */
    public EmptyDescriptionException(TaskType taskType) {
        super("Oh no! The description of a " + getTaskTypeString(taskType) + " cannot be empty.");
    }
    
    private static String getTaskTypeString(TaskType taskType) {
        switch (taskType) {
        case TODO:
            return "todo";
        case DEADLINE:
            return "deadline";
        case EVENT:
            return "event";
        default:
            return null;
        }
    }
}
