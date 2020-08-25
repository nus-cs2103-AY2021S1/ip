package duke.exception;

/**
 * Represents the exception that user does not specify the task they want to delete.
 */
public class DeletionIndexEmptyException extends  DukeException{
    public DeletionIndexEmptyException() {
        super("☹ OOPS!!! The deletion index cannot be empty.");
    }
}
