package taskbot.exceptions;

/**
 * This exception handles when the user tries to
 * complete an already complete class.
 */
public class TaskAlreadyCompleteException extends TaskbotException {
    public TaskAlreadyCompleteException(String msg) {
        super(msg);
    }
}
