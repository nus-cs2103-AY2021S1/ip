package duke.exceptions;

/**
 * Class to initiate InvalidReminderFormatException.
 * Thrown when the format for reminder command is wrong.
 */
public class InvalidReminderFormatException extends DukeException {
    public InvalidReminderFormatException() {
        super("? OOPS!!! Format for reminder command doesnt seem to be correct. Eg. reminder 2");
    }
}
