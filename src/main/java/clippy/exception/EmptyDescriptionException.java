package clippy.exception;

import clippy.task.TaskType;

public class EmptyDescriptionException extends ClippyException {
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
