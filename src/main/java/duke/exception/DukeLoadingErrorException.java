package duke.exception;

/**
 * Represents a DukeException in which a loading error has occurred.
 */
public class DukeLoadingErrorException extends DukeException{
    /**
     * Constructor.
     */
    public DukeLoadingErrorException() {
        super("Loading error!");
    }
}
