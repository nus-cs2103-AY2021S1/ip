package duke.exceptions;

/**
 * Thrown when the user inputs an invalid command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
