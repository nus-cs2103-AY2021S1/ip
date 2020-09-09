/**
 * Represents an exception where the input has no description.
 */
public class NoDescriptionException extends DukeException {

    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
