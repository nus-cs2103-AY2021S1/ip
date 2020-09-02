package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents a special result when the command given by the user is invalid.
 */
public class InvalidCommandResult extends CommandResult {
    public InvalidCommandResult(String commandMessage) {
        super(commandMessage, false, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
