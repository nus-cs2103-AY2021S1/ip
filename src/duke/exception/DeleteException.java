package duke.exception;

/**
 * The exception thrown when the user does not input any number
 * in order to delete a task in the list.
 */
public class DeleteException extends DukeException {

    /**
     * Constructs a DeleteException with default message.
     * The message is "OOPS!!! You need a task number to use delete!."
     */
    public DeleteException() {
        super("OOPS!!! You need a task number to use delete!");
    }
}
