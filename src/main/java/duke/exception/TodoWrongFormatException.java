package duke.exception;

/**
 * Encapsulates a to-do wrong format exception. These exceptions are thrown when the user enters a to-do command with
 * an invalid format (the first word of the command is a valid command word but an error lies elsewhere in the
 * command).
 */
public class TodoWrongFormatException extends WrongFormatException {

    /**
     * Creates and initializes a TodoWrongFormatException object.
     */
    public TodoWrongFormatException() {
        super("todo");
    }

    /**
     * Returns an error message. Informs the user of the valid format of the command.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "description of a task.";
    }
}
