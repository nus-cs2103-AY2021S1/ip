/**
 * Represents an exception thrown when the description of the task is not provided.
 */
public class EmptyDescriptionException extends DukeException {
    EmptyDescriptionException() {
        super("I can't add a task without a description :(");
    }
}
