package duke.command;

/**
 * The class DukeInvalidCommandException denotes a Duke InvalidCommandException.
 *
 * @author Alvin Chee
 */
public class DukeInvalidCommandException extends DukeRunTimeException {
    /**
     * Constructs a DukeInvalidCommandException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeInvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
