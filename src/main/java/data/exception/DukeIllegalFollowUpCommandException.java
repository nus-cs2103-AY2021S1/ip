package data.exception;

/**
 * Signals that a given follow up command is incorrect for a given command by model.Duke.
 */
public class DukeIllegalFollowUpCommandException extends DukeException {

    /**
     * @param command first command
     * @param followUpCommand second command
     */
    public DukeIllegalFollowUpCommandException(String command, String followUpCommand) {
        super("My deepest apologies but '" + followUpCommand + "' is an incorrect follow up command to a '"
                + command + "' command.");
    }
}
