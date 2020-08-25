package duke.exception;

/**
 * Encapsulates an event wrong format exception. These exceptions are thrown when the user enters an event command with
 * an invalid format (the first word of the command is a valid command word but an error lies elsewhere in the
 * command).
 */
public class EventWrongFormatException extends WrongFormatException {

    /**
     * Creates and initializes an EventWrongFormatException object.
     */
    public EventWrongFormatException() {
        super("event");
    }

    /**
     * Returns an error message. Informs the user of the valid format of the command.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "description of a task in the " +
                "following format:\nevent /at time.";
    }
}
