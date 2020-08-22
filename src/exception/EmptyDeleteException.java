package exception;

/**
 * Represents an exception stating the index of the task to be deleted is empty.
 */
public class EmptyDeleteException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index of what to be deleted cannot be empty.";
    }
}
