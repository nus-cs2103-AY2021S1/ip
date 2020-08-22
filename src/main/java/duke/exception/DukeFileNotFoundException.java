package duke.exception;

/**
 * Represents a DukeException in which the file is not found.
 */
public class DukeFileNotFoundException extends DukeException{
    /**
     * Constructor.
     */
    public DukeFileNotFoundException() {
        super("File not found!");
    }
}
