/**
 * Exception that occurs because of an invalid field of a Task.
 */
public class TaskException extends Exception {

    public TaskException(TaskType taskType, String taskProperty, TaskExceptionType taskExceptionType) {
        super("\u2639 OOPS!!! The " + taskProperty + " of a " + taskType.toString() + " " + taskExceptionType);
    }
}
