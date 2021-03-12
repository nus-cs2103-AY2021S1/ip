package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the DeleteCommand.
 */
public class DeleteCommandResult extends CommandResult {
    public DeleteCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
