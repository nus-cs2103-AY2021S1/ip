package duke.exception;

import duke.ImageType;

/**
 * Represents the exception where no match is found for a given keyword.
 */
public class NoMatchFoundException extends DukeException {

    /**
     * Creates a NoMatchFoundException object.
     */
    public NoMatchFoundException() {
        super("There were no tasks that matched the keyword\n"
                + "Try using 'list' to find out what tasks you have!\n", ImageType.ALERT);
    }
}
