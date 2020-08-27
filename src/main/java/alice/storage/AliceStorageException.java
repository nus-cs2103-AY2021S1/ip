package alice.storage;

import alice.AliceException;

/**
 * Thrown when there is an error in writing, reading or creating the ALICE data file.
 */
public class AliceStorageException extends AliceException {
    /**
     * Constructs a <code>AliceStorageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AliceStorageException(String msg) {
        super(msg);
    }
}
