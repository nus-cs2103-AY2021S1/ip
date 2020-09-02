package alice.command.result;

import alice.storage.SaveStatus;

public class DeleteCommandResult extends CommandResult {
    public DeleteCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
