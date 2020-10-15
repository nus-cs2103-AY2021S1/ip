package duke.exception;

/** Represents exceptions when Duke is not able to execute its internal operations. */
public class DukeOperationException extends DukeException {

    /**
     * Constructor method.
     *
     * @param message the error message <code>String</code>.
     */
    public DukeOperationException(String message) {
        super(message);
    }
}
