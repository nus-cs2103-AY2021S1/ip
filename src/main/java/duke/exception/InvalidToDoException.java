package duke.exception;

/**
 * Exception thrown when a todo command has no description.
 */
public class InvalidToDoException extends DukeException {
    public InvalidToDoException() {
        super("The description of a todo cannot be empty :(\n"
                + "The command format is \"todo <task>\"");
    }
}
