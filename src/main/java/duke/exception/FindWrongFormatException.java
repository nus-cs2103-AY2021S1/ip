package duke.exception;

import duke.command.FindCommand;

public class FindWrongFormatException extends WrongFormatException {

    private static final String correctFormatDescription = "word / words that you would like to search for in\nthe"
            + " task list.";

    /**
     * Creates and initializes a FindWrongFormatException object.
     */
    public FindWrongFormatException() {
        super(FindCommand.COMMAND_WORDS.get(0), correctFormatDescription);
    }
}
