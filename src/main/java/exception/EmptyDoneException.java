package exception;

/**
 * Represents an exception stating the index of the task to be marked as done is empty.
 */
public class EmptyDoneException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index of what is done cannot be empty.";
    }
}
