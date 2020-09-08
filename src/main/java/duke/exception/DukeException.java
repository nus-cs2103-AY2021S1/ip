package duke.exception;

/**
 * DukeException is the custom exception class of the Duke program
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 50572093442410692L;

    public DukeException(String message) {
        super(message);
    }
}
