package duke.exceptions;

/**
 * Class to initiate IncorrectReminderInputException.
 * Thrown when the input to reminder command is not in range or not a number.
 */
public class IncorrectReminderInputException extends DukeException {
    public IncorrectReminderInputException() {
        super("Input for reminder command is invalid! Input a number that is more than 0.");
    }
}
