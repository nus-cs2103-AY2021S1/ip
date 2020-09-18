package duke.storage;

/**
 * The class DukeCompileTimeException denotes a Duke checked exception.
 *
 * @author Alvin Chee
 */
public class DukeCompileTimeException extends Exception {
    /**
     * Constructs a DukeCompileTimeException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeCompileTimeException(String errorMessage) {
        super(errorMessage);
    }
}
