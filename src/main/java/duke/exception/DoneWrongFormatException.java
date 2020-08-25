package duke.exception;

/**
 * Encapsulates a done wrong format exception. These exceptions are thrown when the user enters a done command with an
 * invalid format in which the first word of the command is "done" but either no other text accompanies the command, or
 * the text after the word "done" is not a single integer).
 */
public class DoneWrongFormatException extends WrongFormatException {

    /**
     * Creates and initializes a DoneWrongFormatException object.
     */
    public DoneWrongFormatException() {
        super("done");
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
