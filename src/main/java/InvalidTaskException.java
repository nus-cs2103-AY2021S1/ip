/**
 * Represents an exception thrown when there is no such task.
 */
public class InvalidTaskException extends DukeException {
    InvalidTaskException() {
        super("Hmm.. I dont think you have that task in your list.");
    }
}
