package duke.exception;

import duke.ImageType;

/**
 * Represents the exception where the user does not enter an appropriate command.
 */
public class UnknownCommandException extends DukeException {

    /**
     * * Creates an UnknownCommandException object.
     */
    public UnknownCommandException() {
        super("Sorry I didn't understand that\n"
                + "How about entering 'help' instead?\n", ImageType.CROSS);
    }
}
