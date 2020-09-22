package duke.exception;

/**
 * Thrown when issued done input is out of range.
 */
public class DoneOutOfRangeException extends Exception {

    /**
     * Constructs the exception.
     */
    public DoneOutOfRangeException() {
        super("\uD83D\uDE41 OOPS! Such a task does not exist.");
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
