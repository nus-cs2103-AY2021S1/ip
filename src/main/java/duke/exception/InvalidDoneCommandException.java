package duke.exception;

import duke.ImageType;

/**
 * Represents the exception where the user is trying to mark an already marked tasked as done.
 */
public class InvalidDoneCommandException extends DukeException {

    /**
     * Creates an InvalidDoneCommandException object.
     */
    public InvalidDoneCommandException() {
        super("This task is already done!\n"
                + "Check out each task's status by using 'list'!\n", ImageType.ALERT);
    }
}
