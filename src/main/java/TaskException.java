public class TaskException extends Exception {

    public TaskException(TaskType taskType, String taskProperty, String msg) {
        super("\u2639 OOPS!!! The " + taskProperty + " of a " + taskType.toString() + " " + msg);
    }
}
