package clippy.exception;

/**
 * Represents an exception due to missing save file for the program.
 */
public class NoSavedFileException extends ClippyException {
    /**
     * Constructs an NoSavedFileException with a pre-defined error message.
     */
    public NoSavedFileException() {
        super("No save file for tasks found. Automatically created new save file under root/data");
    }
}
