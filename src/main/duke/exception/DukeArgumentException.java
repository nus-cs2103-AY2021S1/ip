package duke.exception;

/**
 * Exception to be thrown if the arguments for a command are not valid.
 */
public class DukeArgumentException extends Exception {
    public DukeArgumentException(String error) {
        super(error);
    }
}
