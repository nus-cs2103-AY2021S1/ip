package duke.commands;

/**
 * Encapsulates the result after execution of a command.
 */
public class CommandResult {
    /**
     * Message to output to the user after execution of a command.
     */
    private String messageAfterExecution;

    /**
     * Contructs a CommandResult object with the given output message to the user.
     *
     * @param messageAfterExecution Message to output to the user.
     */
    public CommandResult(String messageAfterExecution) {
        this.messageAfterExecution = messageAfterExecution;
    }

    /**
     * Getter method for the message to output to the user after execution of a command.
     *
     * @return Message to output to the user.
     */
    public String getMessageAfterExecution() {
        return this.messageAfterExecution;
    }
}
