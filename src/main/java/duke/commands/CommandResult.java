package duke.commands;

public class CommandResult {
    private String messageAfterExecution;

    public CommandResult(String messageAfterExecution) {
        this.messageAfterExecution = messageAfterExecution;
    }

    public String getMessageAfterExecution() {
        return this.messageAfterExecution;
    }
}
