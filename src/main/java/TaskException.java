public class TaskException extends Exception {

    public TaskException(TaskType taskType, String taskProperty, String msg) {
        super("☹ OOPS!!! The " + taskProperty + " of a " + taskType.toString() + " " + msg);
    }
}
