package exceptions;

/**
 * Class to initiate TaskCompletedException.
 * Thrown when the task is already done and the user wants to mark this task
 * as done again.
 */
public class TaskCompletedException extends DukeException{
    public TaskCompletedException() {
        super("☹ OOPS!!! Task is already done!");
    }
}
