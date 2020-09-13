/**
 * The program runs into a invalid Event exception.
 */
public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super(" OOPS!!! The description of a Event cannot be empty.");
    }
}
