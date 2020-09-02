/**
 * Inherits from the DukeException class and is thrown
 * when the command "done" or "delete" is used without
 * indicating a task number.
 */
public class MissingNumberException extends DukeException {

    /**
     * Creates a MissingNumberException object.
     */
    public MissingNumberException() {
        super("Please indicate the task number.");
    }
}
