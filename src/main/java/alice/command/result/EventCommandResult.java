package alice.command.result;

import alice.storage.SaveStatus;

public class EventCommandResult extends CommandResult {
    public EventCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
