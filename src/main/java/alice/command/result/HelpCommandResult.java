package alice.command.result;

import alice.storage.SaveStatus;

public class HelpCommandResult extends CommandResult {
    public HelpCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
