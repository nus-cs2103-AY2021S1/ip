package duke.exception;

/** Represents the exceptions in Duke. */
public class DukeException extends Exception {

    /**
     * Constructor method
     *
     * @param msg the error message <code>String</code>.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Converts the exception into a <code>String</code>.
     *
     * @return the error message <code>String</code>.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
