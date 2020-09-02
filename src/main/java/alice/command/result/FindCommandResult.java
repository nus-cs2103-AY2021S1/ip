package alice.command.result;

import alice.storage.SaveStatus;

public class FindCommandResult extends CommandResult {
    public FindCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
