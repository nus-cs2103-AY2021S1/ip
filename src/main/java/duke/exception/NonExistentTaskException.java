package duke.exception;

import duke.ImageType;

/**
 * Represents the exception where the user tries to access a task that is not on the list.
 */
public class NonExistentTaskException extends DukeException {

    /**
     * Creates a NonExistentTaskException object.
     */
    public NonExistentTaskException() {
        super("Sorry that task doesn't exist\n"
                + "Try using 'list' to find out what tasks you have!\n", ImageType.CROSS);
    }
}
