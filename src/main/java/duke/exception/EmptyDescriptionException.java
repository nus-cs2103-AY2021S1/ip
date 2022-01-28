package duke.exception;

/**
 * Thrown when the task added is missing a description.
 */
public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String target) {
        super(String.format("The %s should not be empty", target));
    }

    public String toString() {
        return getMessage();
    }
}
