package duke.storage;

/**
 * The class DukeIOException denotes a Duke IOexception.
 *
 * @author Alvin Chee
 */
public class DukeIOException extends DukeCompileTimeException {
    /**
     * Constructs a DukeCompileTimeException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeIOException(String errorMessage) {
        super(errorMessage);
    }
}
