package duke.exception;

public class StorageIoException extends DukeException {
    public StorageIoException(String message) {
        super("Error! " + message);
    }
}
