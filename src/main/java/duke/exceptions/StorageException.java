package duke.exceptions;

import static duke.utils.Messages.MESSAGE_STORAGE_EXCEPTION;

/**
 * Thrown to indicate that there is a problem saving the file.
 */
public class StorageException extends DukeException {

    /**
     * Constructs a StorageException with the relevant detail message.
     */
    public StorageException() {
        super(MESSAGE_STORAGE_EXCEPTION);
    }
}
