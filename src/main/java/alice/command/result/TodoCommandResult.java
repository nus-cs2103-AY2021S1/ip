package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the TodoCommand.
 */
public class TodoCommandResult extends CommandResult {
    public TodoCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
