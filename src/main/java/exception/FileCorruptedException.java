package exception;

public class FileCorruptedException extends DukeException {
    private static final String DESCRIPTION = "File is corrupted. Unable to convert file into list of Tasks.";
    public FileCorruptedException() {
        super(DESCRIPTION);
    }
    public FileCorruptedException(String message) {
        super(message);
    }
}
