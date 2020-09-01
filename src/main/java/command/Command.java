package command;

import executor.CommandExecutor;

public class Command {

    String input;

    CommandExecutor commandExecutor;

    public Command(String input, CommandExecutor commandExecutor) {
        this.input = input;
        this.commandExecutor = commandExecutor;
    }

    public String getInput() {
        return input;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
