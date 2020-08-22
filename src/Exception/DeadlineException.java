package Exception;

/**
 * Represents the exception that the description of a deadline is empty.
 */
public class DeadlineException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! The description of a deadline cannot be empty.\n";
        return s;
    }
}
