package duke.exception;

/**
 * Represents a Duke exception in which a file cannot be loaded.
 */
public class DukeFileNotFoundException extends DukeException {
    @Override
    public String toString() {
        return "ERROR: File to load cannot be found";
    }
}
