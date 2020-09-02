package alice.command.result;

import alice.storage.SaveStatus;

public class DeadlineCommandResult extends CommandResult {
    public DeadlineCommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        super(commandMessage, isCommandSuccess, saveStatus);
    }
}
