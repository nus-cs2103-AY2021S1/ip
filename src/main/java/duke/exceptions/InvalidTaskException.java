package duke.exceptions;

/**
 * Represents an exception thrown when there is no such task.
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Oh no.. I dont think you have that task in your list.");
    }
}
