package duke;

/**
 * Represents an exception that occurs when the save file being read has issues.
 */
public class InvalidSaveException extends Exception {

    /**
     * Constructs a new <code>InvalidSaveException</code> with the given exception message.
     *
     * @param message details of the exception.
     */
    public InvalidSaveException(String message) {
        super(message);
    }

}
