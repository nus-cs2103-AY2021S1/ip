package duke.exception;

public class CorruptedStorageException extends DukeException {
    public CorruptedStorageException(String message) {
        super("Storage is corrupted!" + message);
    }
}
