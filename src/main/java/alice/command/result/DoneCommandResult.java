package alice.command.result;

import alice.storage.SaveStatus;

public class DoneCommandResult extends CommandResult {
    public DoneCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
