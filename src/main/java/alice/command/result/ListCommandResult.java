package alice.command.result;

import alice.storage.SaveStatus;

public class ListCommandResult extends CommandResult {
    public ListCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
