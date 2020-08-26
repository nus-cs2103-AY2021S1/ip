package duke.exception;

/**
 * Thrown when issued event input has no description.
 */
public class EmptyEventException extends Exception {

    /**
     * Constructs the exception.
     */
    public EmptyEventException() {
        super("\uD83D\uDE41 OOPS! The description of an event cannot be empty.");
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
