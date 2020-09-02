package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the DeadlineCommand.
 */
public class DeadlineCommandResult extends CommandResult {
    public DeadlineCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
