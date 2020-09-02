package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the FindCommand.
 */
public class FindCommandResult extends CommandResult {
    public FindCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
