/**
 * Thrown to indicate that a command to find tasks is invalid. The command is invalid
 * if no keywords are specified.
 */

public class InvalidFindException extends Exception {

    /**
     * Constructs a InvalidFindException with the specified detail message.
     * @param message Detail message
     */
    public InvalidFindException(String message) {
        super(message);
    }
}
