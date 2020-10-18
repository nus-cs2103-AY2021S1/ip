package duke.exceptions;

/**
 * An exception which indicates that the time is invalid.
 */
public class InvalidTimeException extends Exception {
    /**
     * Creates a new InvalidTimeException.
     *
     * @param message The message of this exception
     */
    public InvalidTimeException(String message){
        super(message);
    }

}
