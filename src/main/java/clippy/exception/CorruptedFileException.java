package clippy.exception;

/**
 * Represents an exception due to the save file data being corrupted.
 */
public class CorruptedFileException extends ClippyException {
    /**
     * Constructs a CorruptedFileException with a pre-defined error message.
     */
    public CorruptedFileException() {
        super("Save file is corrupted, unable to load tasks.");
    }
}
