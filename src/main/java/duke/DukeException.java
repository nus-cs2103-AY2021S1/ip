package duke;

/**
 * Thrown to indicate that the chat bot has encountered a problem.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
