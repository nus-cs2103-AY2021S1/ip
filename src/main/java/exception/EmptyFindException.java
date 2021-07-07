package exception;

/**
 * Represents an exception stating the keyword to find is empty.
 */
public class EmptyFindException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The keyword cannot be empty";
    }
}
