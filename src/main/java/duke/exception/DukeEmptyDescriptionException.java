package duke.exception;

/**
 * Represents an exception thrown when description of a Task is left empty.
 */
public class DukeEmptyDescriptionException extends DukeTaskException {
    public DukeEmptyDescriptionException(String task) {
        super(String.format("☹ OOPS!!! The description of %s cannot be empty.", task));
    }
}
