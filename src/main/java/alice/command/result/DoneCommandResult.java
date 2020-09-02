package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the DoneCommand.
 */
public class DoneCommandResult extends CommandResult {
    public DoneCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
