package duke.exception;

/**
 * Exception thrown when a forbidden character is used as a description of a task.
 */
public class ForbiddenCharacterException extends DukeException {
    /**
     * Constructs an ForbiddenCharacterException.
     */
    public ForbiddenCharacterException() {
        super("Sorry :-(\n\"|\" is not allowed in a command or task description");
    }
}
