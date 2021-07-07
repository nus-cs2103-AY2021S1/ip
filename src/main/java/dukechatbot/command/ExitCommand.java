package dukechatbot.command;

import dukechatbot.executor.ExitCommandExecutor;

/**
 * Represents the command for delete.
 */
public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input, new ExitCommandExecutor());
    }
}

