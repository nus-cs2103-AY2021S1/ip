package duke.exceptions;
/**
 * An exception which indicates that the command is invalid.
 */
public class InvalidRequestException extends Exception{
    public InvalidRequestException(String message) {
        super(message);
    }
}
