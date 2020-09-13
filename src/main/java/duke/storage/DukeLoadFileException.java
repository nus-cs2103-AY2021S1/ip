package duke.storage;

/**
 * The class DukeLoadFileException denotes a DukeLoadFilexception.
 *
 * @author Alvin Chee
 */
public class DukeLoadFileException extends DukeCompileTimeException {
    /**
     * Constructs a DukeCompileTimeException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeLoadFileException(String errorMessage) {
        super(errorMessage);
    }
}

