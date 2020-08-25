package duke.exception;

/**
 * Encapsulates a deadline wrong format exception. These exceptions are thrown when the user enters a deadline command
 * with an invalid format (the first word of the command is a valid command word but an error lies elsewhere in the
 * command).
 */
public class DeadlineWrongFormatException extends WrongFormatException {

    /**
     * Creates and initializes a DeadlineWrongFormatException object.
     */
    public DeadlineWrongFormatException() {
        super("deadline");
    }

    /**
     * Returns an error message. Informs the user of the valid format of the command.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "description of a task in the " +
                "following format:\ntask /by YYYY-MM-DD hhmm\nwhere hh is hours and mm is minutes.\nPlease ensure " +
                "that the date and time are valid.";
    }
}
