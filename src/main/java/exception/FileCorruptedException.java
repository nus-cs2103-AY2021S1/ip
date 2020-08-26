package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's operation when
 *  loading file from computer.
 *
 * <p>The 'FileCorruptedException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class FileCorruptedException extends DukeException {
    private static final String DESCRIPTION = "File is corrupted. Unable to convert file into list of Tasks.";

    /**
     * Constructor of this object.
     */
    public FileCorruptedException() {
        super(DESCRIPTION);
    }

    /**
     * Constructor of this object.
     * @param message error message of this exception.
     */
    public FileCorruptedException(String message) {
        super(message);
    }
}
