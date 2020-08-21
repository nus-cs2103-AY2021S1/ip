package duke.exception;

/**
 * Represents a Duke exception in which the input's task description is invalid.
 */
public class DukeInvalidTaskDescriptionException extends DukeTaskException {
    @Override
    public String toString() {
        return "ERROR: The description of a task cannot be empty!";
    }
}
