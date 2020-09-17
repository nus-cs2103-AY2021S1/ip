package bob.exception;

/**
 * An exception to be thrown when attempting to snooze a task with no deadline.
 */
public class BobNotADeadlineException extends BobException {

    /**
     * Returns a message indicating that the task may not be snoozed because it does not have a deadline.
     *
     * @return a message indicating that the task may not be snoozed because it does not have a deadline.
     */
    public String getMessage() {
        return "This task does not have a deadline and therefore may not be snoozed.";
    }
}
