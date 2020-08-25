/**
 * The exception that is thrown when a user intends to act on a task number that does not exist.
 */
public class InvalidTaskNumber extends DukeException {
    public InvalidTaskNumber() {
        super("Aiyo, don't have this task number leh...");
    }
}
