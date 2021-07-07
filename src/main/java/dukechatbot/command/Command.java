package dukechatbot.command;

import java.util.Objects;

import dukechatbot.executor.CommandExecutor;

/**
 * Represents the command.
 * Stores the input of the command.
 * Stores the executor related to the command.
 */
public class Command {

    private String input;

    private CommandExecutor commandExecutor;

    public Command(String input, CommandExecutor commandExecutor) {
        this.input = input;
        this.commandExecutor = commandExecutor;
        assert(!Objects.isNull(this.input));
        assert(!Objects.isNull(this.commandExecutor));
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
