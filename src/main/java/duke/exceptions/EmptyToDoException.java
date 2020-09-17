package duke.exceptions;

/**
 * Thrown when the the user inputs an empty Todo description.
 */
public class EmptyToDoException extends DukeException {
    public EmptyToDoException() {
        super("\u2639 OOPS!!! The description of a todo cannot be empty.");
    }
}
