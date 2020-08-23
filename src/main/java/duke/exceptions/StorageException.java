package duke.exceptions;

public class StorageException extends DukeException {

    public StorageException() {
        super("OOPS! Error loading file.");
    }
}
