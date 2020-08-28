package duke.exceptions;

/**
 * Class to initiate InvalidToDoCommandException.
 * Thrown when the format for todo command is wrong.
 */
public class InvalidToDoFormatException extends DukeException {
    public InvalidToDoFormatException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
