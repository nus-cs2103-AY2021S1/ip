package duke.exception;

/**
 * Thrown when input is not recognised by Duke.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs the exception.
     */
    public UnknownCommandException() {
        super("\uD83D\uDE41 OOPS! I'm sorry, but I don't know what that means :-(");
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
