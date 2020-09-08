package duke.exception;

import duke.command.DoneCommand;

/**
 * Encapsulates a done wrong format exception. These exceptions are thrown when the user enters a done command with an
 * invalid format in which the first word of the command is "done" but either no other text accompanies the command, or
 * the text after the word "done" is not a single integer).
 */
public class DoneWrongFormatException extends WrongFormatException {

    private static final String correctFormatDescription = "single whitespace and an integer";

    /**
     * Creates and initializes a DoneWrongFormatException object.
     */
    public DoneWrongFormatException() {
        super(DoneCommand.COMMAND_WORDS.get(0), correctFormatDescription);
    }
}
