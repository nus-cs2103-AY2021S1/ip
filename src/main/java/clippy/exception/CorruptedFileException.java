package clippy.exception;

public class CorruptedFileException extends ClippyException {
    public CorruptedFileException() {
        super("Save file is corrupted, unable to load tasks.");
    }
}
