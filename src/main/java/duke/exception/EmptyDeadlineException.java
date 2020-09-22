package duke.exception;

/**
 * Thrown when issued deadline input has no description.
 */
public class EmptyDeadlineException extends Exception {

    /**
     * Constructs the exception.
     */
    public EmptyDeadlineException() {
        super("\uD83D\uDE41 OOPS! The description of a deadline cannot be empty.");
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
