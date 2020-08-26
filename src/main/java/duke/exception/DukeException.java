package duke.exception;

/**
 * A generic <code>Exception</code> created by Duke.
 */
public class DukeException extends Exception{

    /**
     * Creates a new <code>DukeException</code> with error details encoded within.
     *
     * @param message Error details.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns String representation of <code>DukeException</code> for display to user.
     *
     * @return Formatter String representation.
     */
    @Override
    public String getMessage() {
        return "DUKE ERROR - " + super.getMessage();
    }

}
