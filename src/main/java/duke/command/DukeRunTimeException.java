package duke.command;

/**
 * The class DukeRunTimeException denotes a Duke RunTimeException.
 *
 * @author Alvin Chee
 */
public class DukeRunTimeException extends RuntimeException {
    /**
     * Constructs a DukeRunTimeException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeRunTimeException(String errorMessage) {
        super(errorMessage);
    }
}
