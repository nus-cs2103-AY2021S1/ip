public class NoSavedFileException extends ClippyException {
    NoSavedFileException() {
        super("No save file for tasks found. Automatically created new save file under root/data");
    }
}
