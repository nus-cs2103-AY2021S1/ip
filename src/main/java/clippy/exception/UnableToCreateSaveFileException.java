package clippy.exception;

/**
 * Represents an exception due to inability to create a save file in a local directory.
 */
public class UnableToCreateSaveFileException extends ClippyException {
    /**
     * Constructs an UnableToCreateSaveFileException with a pre-defined error message.
     */
    public UnableToCreateSaveFileException() {
        super("Unable to create save file.");
    }
}
