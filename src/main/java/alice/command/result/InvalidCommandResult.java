package alice.command.result;

import alice.storage.SaveStatus;

public class InvalidCommandResult extends CommandResult {
    public InvalidCommandResult(String commandMessage) {
        super(commandMessage, false, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
