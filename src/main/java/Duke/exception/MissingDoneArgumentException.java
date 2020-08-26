package duke.exception;

/**
 * Thrown when issued done input has no argument.
 */
public class MissingDoneArgumentException extends Exception {

    /**
     * Constructs the exception.
     */
    public MissingDoneArgumentException() {
        super("\uD83D\uDE41 OOPS! You have to specify which task you want to mark as done.");
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
