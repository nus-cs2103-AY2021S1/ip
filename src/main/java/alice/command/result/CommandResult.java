package alice.command.result;

import alice.storage.SaveStatus;

public class CommandResult {
    private final String commandMessage;
    private final SaveStatus saveStatus;
    private final boolean isCommandSuccess;

    public CommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        this.commandMessage = commandMessage;
        this.saveStatus = saveStatus;
        this.isCommandSuccess = isCommandSuccess;
    }

    public SaveStatus getSaveStatus() {
        return saveStatus;
    }

    public String getMessage() {
        return commandMessage;
    }

    public boolean isCommandFailure() {
        return !isCommandSuccess;
    }

    /**
     * Checks if the command is used to exit the ALICE program.
     *
     * @return true if the command exits the ALICE program; false otherwise.
     */
    public boolean shouldExit() {
        return false;
    }
}
