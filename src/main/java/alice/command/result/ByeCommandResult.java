package alice.command.result;

import alice.storage.SaveStatus;

public class ByeCommandResult extends CommandResult {
    public ByeCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
