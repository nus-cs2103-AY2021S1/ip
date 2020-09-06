package duke.exceptions;

import duke.storage.Storage.StorageOperationException;

/**
 * Signals that input from save file is not in the right format.
 */
public class IllegalValueException extends StorageOperationException {

    public IllegalValueException(String message)
    {
        super(message);
    }

}
