package duke.exception;

public class StorageIOException extends DukeException {
    public StorageIOException(String message) {
        super("Error! " + message);
    }
}
