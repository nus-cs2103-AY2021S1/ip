package alice.command.result;

import alice.storage.SaveStatus;

/**
 * Represents the result of processing a user command.
 * It contains a set of instructions which serves to provide the appropriate feedback to the user.
 */
public abstract class CommandResult {
    private final String commandMessage;
    private final SaveStatus saveStatus;
    private final boolean isCommandSuccess;

    /**
     * Creates a new command result with the specific message and command result status.
     *
     * @param commandMessage   the message to be returned to the user as feedback.
     * @param isCommandSuccess the success status of the command execution.
     * @param saveStatus       the status of the save process during command execution.
     */
    public CommandResult(String commandMessage, boolean isCommandSuccess, SaveStatus saveStatus) {
        this.commandMessage = commandMessage;
        this.saveStatus = saveStatus;
        this.isCommandSuccess = isCommandSuccess;

        assert !commandMessage.isBlank() : "Cannot create CommandResult without a message";
    }

    /**
     * Gets the status of the save process during command execution.
     * Returns a SaveStatus.SAVE_NOT_APPLICABLE in the case when a save process
     * is not supposed to be executed.
     *
     * @return the corresponding SaveStatus type.
     */
    public SaveStatus getSaveStatus() {
        return saveStatus;
    }

    /**
     * Gets the output message of the command execution.
     *
     * @return the output message.
     */
    public String getMessage() {
        return commandMessage;
    }

    /**
     * Checks if the command execution failed.
     * The message of command result will be the corresponding error
     * message in the case of command execution failure.
     *
     * @return true if the command execution failed; false otherwise.
     */
    public boolean isCommandFailure() {
        return !isCommandSuccess;
    }

    /**
     * Checks if the command should exit the ALICE program after command execution.
     *
     * @return true if the command exits the ALICE program; false otherwise.
     */
    public boolean shouldExit() {
        return false;
    }
}
