package duke.exceptions;

/**
 * Class to initiate InvalidCommandException.
 * Thrown when input command does not match any of the existing duke.commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs the InvalidCommandException.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! Sorry I do not understand this command!\n"
                + "Input the \"help\" command to see the list of supported commands."
        );
    }
}
