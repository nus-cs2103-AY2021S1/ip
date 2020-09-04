package duke.exceptions;

/**
 * Class to initiate InvalidHelpCommandException.
 * Thrown when there are extra input after the help command.
 */
public class InvalidHelpCommandException extends DukeException {
    public InvalidHelpCommandException() {
        super("Help command is invalid! Input \"help\" to show the list of available commands.");
    }
}
