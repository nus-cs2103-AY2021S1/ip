package duke.exception;

/**
 * Thrown when issued event input has no date.
 */
public class MissingEventDateException extends Exception {

    /**
     * Constructs the exception.
     */
    public MissingEventDateException() {
        super("\uD83D\uDE41 OOPS! The date of an event cannot be empty.");
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
