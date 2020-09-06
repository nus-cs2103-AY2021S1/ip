package exception;

/**
 * Parent exception class of all exceptions that
 * logic.Duke is expected to experience
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
