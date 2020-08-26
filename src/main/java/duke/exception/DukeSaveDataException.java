package duke.exception;

/**
 * <code>Exception</code> thrown by Duke related to saving or loading from save files.
 */
public class DukeSaveDataException extends DukeException{

    /**
     * Creates a new <code>DukeSaveDataException</code> with the given error details.
     *
     * @param message Error details.
     */
    public DukeSaveDataException(String message) {
        super(message);
    }
}
