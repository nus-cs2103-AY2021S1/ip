package duke.exception;

/**
 * Represents the exception where the user is interact with an empty task list.
 */
public class EmptyListException extends DukeException {

    /**
     * Creates an EmptyListException object.
     */
    public EmptyListException() {
        super("There are no tasks on your list\n"
                + "Use the 'add' command to start adding tasks!\n");
    }
}
