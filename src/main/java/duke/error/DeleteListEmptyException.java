package duke.error;

/**
 * Represents an error that occurs when the user tries to delete from an empty list.
 *
 * @author Roger Lim
 */
public class DeleteListEmptyException extends Exception {
    public DeleteListEmptyException() {
        super("List is empty, I cannot do that.");
    }
}
