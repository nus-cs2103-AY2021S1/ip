/**
 * The program runs into a invalid Deadline exception.
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super(" OOPS!!! The description of a Deadline cannot be empty.");
    }
}
