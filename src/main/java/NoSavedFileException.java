public class NoSavedFileException extends DukeException {
    NoSavedFileException() {
        super("No save file for tasks found. Automatically created new save file under root/data");
    }
}
