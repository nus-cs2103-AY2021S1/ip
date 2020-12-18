package duke.exception;

/**
 * Represents the possible exceptions that can be thrown when the programme fails to write to / read from storage.
 */
public class StorageAccessException extends DukeException {
    /**
     * Instantiates a new StorageAccessException object.
     * @param errorMessage The detail error message.
     */
    public StorageAccessException(String errorMessage) {
        super(errorMessage);
    }
}
