package duke.exception;

import duke.ImageType;

/**
 * Represents the exception where the user does not enter the correct format for a task description.
 */
public class WrongFormatException extends DukeException {

    /**
     * Creaes a WrongFormatException object.
     */
    public WrongFormatException() {
        super("Hmmm, looks like that didn't work\n"
                + "Make sure you used the right format!\n", ImageType.CROSS);
    }
}
