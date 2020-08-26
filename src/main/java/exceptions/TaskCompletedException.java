package exceptions;

public class TaskCompletedException extends DukeException{
    public TaskCompletedException() {
        super("☹ OOPS!!! Task is already done!");
    }
}
