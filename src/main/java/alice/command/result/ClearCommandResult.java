package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the ClearCommand.
 */
public class ClearCommandResult extends CommandResult {
    public ClearCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
