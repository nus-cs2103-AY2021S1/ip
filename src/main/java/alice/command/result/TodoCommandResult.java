package alice.command.result;

import alice.storage.SaveStatus;

public class TodoCommandResult extends CommandResult {
    public TodoCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
