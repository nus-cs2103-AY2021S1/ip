package clippy.exception;

public class NoSavedFileException extends ClippyException {
    public NoSavedFileException() {
        super("No save file for tasks found. Automatically created new save file under root/data");
    }
}
