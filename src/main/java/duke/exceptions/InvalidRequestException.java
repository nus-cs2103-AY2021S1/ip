package duke.exceptions;
/**
 * An exception which indicates that the command is invalid.
 */
public class InvalidRequestException extends Exception{
    /**
     * Create a new InvalidRequestException.
     *
     * @param message The message of this exception
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
