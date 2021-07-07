package duke.exception;

/**
 * Represents an exception when data file contains unrecognized text.
 */
public class CorruptDataException extends DukeException {

    /**
     * Constructs a CorruptDataException.
     */
    public CorruptDataException() {
        super("OOPS!!! Your data file is corrupted. Skipping task creation.");
    }
}
