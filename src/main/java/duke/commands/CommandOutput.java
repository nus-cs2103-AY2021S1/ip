package duke.commands;

public class CommandOutput {
    private String commandOutput;
    private boolean isExit;

    public CommandOutput(String commandOutput, boolean isExit) {
        this.commandOutput = commandOutput;
        this.isExit = isExit;
    }

    public String getCommandOutput() {
        return this.commandOutput;
    }

    public boolean isExit() {
        return this.isExit;
    }

}
