package taskbot.exceptions;

public class TaskAlreadyCompleteException extends Exception {
    public TaskAlreadyCompleteException(String msg) {
        super(msg);
    }
}
