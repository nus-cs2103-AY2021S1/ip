package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the ByeCommand.
 */
public class ByeCommandResult extends CommandResult {
    public ByeCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
