package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of executing the HelpCommand.
 */
public class HelpCommandResult extends CommandResult {
    public HelpCommandResult(String commandMessage, boolean isCommandSuccess) {
        super(commandMessage, isCommandSuccess, SaveStatus.SAVE_NOT_APPLICABLE);
    }
}
