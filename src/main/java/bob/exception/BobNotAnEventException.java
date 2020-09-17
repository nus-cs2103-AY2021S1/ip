package bob.exception;

/**
 * An exception to be thrown when attempting to reschedule task that is not an event.
 */
public class BobNotAnEventException extends BobException {

    /**
     * Returns a message indicating that the task may not be reschedule since it is not an event.
     *
     * @return a message indicating that the task may not be reschedule since it is not an event.
     */
    public String getMessage() {
        return "This task is not an event and therefore may not be rescheduled.";
    }
}
