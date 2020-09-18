package duke.exception;

/**
 * Represents a custom exception for Duke program.
 *
 * @author Tee Kok Siang
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     *
     * @param errorMessage Error message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
