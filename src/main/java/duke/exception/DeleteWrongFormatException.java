package duke.exception;

import duke.command.DeleteCommand;

/**
 * Encapsulates a delete wrong format exception. These exceptions are thrown when the user enters a delete command with
 * an invalid format in which the first word of the command is "delete" but either no other text accompanies the
 * command, or the text after the word "delete" is not a single integer).
 */
public class DeleteWrongFormatException extends WrongFormatException {

    private static final String correctFormatDescription = "single whitespace and an integer";

    /**
     * Creates and initializes a DeleteWrongFormatException object.
     */
    public DeleteWrongFormatException() {
        super(DeleteCommand.COMMAND_WORD, correctFormatDescription);
    }
}
