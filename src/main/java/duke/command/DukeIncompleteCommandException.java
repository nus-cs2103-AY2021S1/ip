package duke.command;

/**
 * The class DukeIncompleteCommandException denotes a Duke IncompleteCommandException.
 *
 * @author Alvin Chee
 */
public class DukeIncompleteCommandException extends DukeRunTimeException {
    /**
     * Constructs a DukeIncompleteCommandException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeIncompleteCommandException(String errorMessage) {
        super(errorMessage);
    }
}
