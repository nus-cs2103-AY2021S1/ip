package taskbot.exceptions;

public class TaskAlreadyCompleteException extends TaskbotException {
    public TaskAlreadyCompleteException(String msg) {
        super(msg);
    }
}
