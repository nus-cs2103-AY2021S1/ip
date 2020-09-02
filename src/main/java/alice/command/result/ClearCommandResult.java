package alice.command.result;

import alice.storage.SaveStatus;

public class ClearCommandResult extends CommandResult {
    public ClearCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
