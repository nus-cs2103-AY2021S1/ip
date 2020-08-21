package duke.exception;

/**
 * Represents a Duke exception in which a loading error occurs.
 */
public class DukeLoadingErrorException extends DukeException {

    @Override
    public String toString() {
        return "ERROR: Loading error";
    }

}
