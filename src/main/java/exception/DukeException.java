package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's operation.
 *
 * <p>The 'DukeException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class DukeException extends Exception {

    /**
     * Constructor of this object.
     *
     * @param message error message of this exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * String representation of this object.
     *
     * @return string representation of this object. Contains display frame and error message.
     */
    @Override
    public String toString() {
        String errorMessage = "     (~.^.~)  OOPS!!! "
                + getMessage()
                + "\n";

        return errorMessage;
    }
}
