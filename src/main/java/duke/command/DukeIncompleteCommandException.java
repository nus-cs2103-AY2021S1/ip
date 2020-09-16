package duke.command;

/**
 * The class DukeIncompleteCommandException denotes a Duke IncompleteCommandException.
 *
 * @author Alvin Chee
 */
public class DukeIncompleteCommandException extends DukeRunTimeException {
    /**
     * Constructs a DukeIncompleteCommandException with error message.
     */
    public DukeIncompleteCommandException() {
        super("The command is incomplete Poppins :D");
    }
}
