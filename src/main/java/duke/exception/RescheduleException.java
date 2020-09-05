package duke.exception;

/**
 * The exception thrown when the user try to reschedule a todo Task.
 */
public class RescheduleException extends DukeException {

    /**
     * Constructs an RescheduleException with default message.
     * The message is "You can't reschedule a Todo Task!".
     */
    public RescheduleException() {
        super("You can't reschedule a Todo Task!");
    }
}
