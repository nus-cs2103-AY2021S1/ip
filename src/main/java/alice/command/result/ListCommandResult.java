package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the ListCommand.
 */
public class ListCommandResult extends CommandResult {
    public ListCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
