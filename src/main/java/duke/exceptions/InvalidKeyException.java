package duke.exceptions;

/**
 * InvalidKeyException is an exception that indicates that the key word is invalid.
 */
public class InvalidKeyException extends Exception{
    /**
     * Creates a new InvalidKeyException.
     *
     * @param message The message of this exception
     */
    public InvalidKeyException(String message){
        super(message);
    }
}
