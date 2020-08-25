package duke.exception;

/**
 * Encapsulates a delete wrong format exception. These exceptions are thrown when the user enters a delete command with
 * an invalid format in which the first word of the command is "delete" but either no other text accompanies the
 * command, or the text after the word "delete" is not a single integer).
 */
public class DeleteWrongFormatException extends WrongFormatException {

    /**
     * Creates and initializes a DeleteWrongFormatException object.
     */
    public DeleteWrongFormatException() {
        super("delete");
    }

    /**
     * Returns an error message. Informs the user of the valid format of the command.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "single whitespace and an integer";
    }
}