package storage;

public class StorageException extends Exception {
    public final String message;
    public final Throwable error;

    /**
     * Create a StorageException generated from running Storage.
     *
     * @param message message for the exception generated.
     * @param error   throwable error resulting in StorageException generated.
     * @return StorageException
     */
    public StorageException(String message, Throwable error) {
        this.error = error;
        this.message = message;
    }
}
