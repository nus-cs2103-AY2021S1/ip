package duke.exceptions;

/**
 * Represents an exception thrown when the description of the task is not provided.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("Oh no, I can't add a task without a description :(");
    }
}
