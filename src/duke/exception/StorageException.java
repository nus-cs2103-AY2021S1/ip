package duke.exception;

import duke.exception.DukeException;

public class StorageException extends DukeException {

    /**
     * Constructs the storage exception for error found in accessing the file.
     * @param msg Error message.
     */
    public StorageException(String msg) {
        super(msg);
    }

}
