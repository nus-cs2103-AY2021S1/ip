package duke.exception;

/**
 * The exception thrown when the user input invalid argument or format when using reschedule.
 */
public class RescheduleFormatException extends DukeException {

    /**
     * Constructs an RescheduleFormatException with default message.
     * The message is "Invalid argument or format! Please put it in the format of "reschedule <task number> <date>"
     * such as "reschedule 1 2020-01-01!".
     */
    public RescheduleFormatException() {
        super("Invalid argument or format! Please put it in the format of \"reschedule <task number> <date>\""
                + "such as \"reschedule 1 2020-01-01!\"");
    }
}
