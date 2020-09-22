package duke.exception;

/**
 * Thrown when issued deadline input has no date.
 */
public class MissingDeadlineDateException extends Exception {

    /**
     * Constructs the exception.
     */
    public MissingDeadlineDateException() {
        super("\uD83D\uDE41 OOPS! The date of a deadline cannot be empty.");
    }

    /**
     * String representation of this exception.
     * @return  String representation of this exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
