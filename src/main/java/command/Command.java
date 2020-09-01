package command;

import executor.CommandExecutor;

/**
 * Represents the command. 
 * Stores the input of the command.
 * Stores the executor related to the command.
 */
public class Command {

    String input;

    CommandExecutor commandExecutor;

    public Command(String input, CommandExecutor commandExecutor) {
        this.input = input;
        this.commandExecutor = commandExecutor;
    }

    /**
     * Returns the CommandExecutor object
     * related to this Command object.
     *
     * @return CommandExecutor object.
     */
    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
