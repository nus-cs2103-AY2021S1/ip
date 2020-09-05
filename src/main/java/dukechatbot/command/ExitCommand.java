package dukechatbot.command;

import dukechatbot.executor.ExitCommandExecutor;

public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input, new ExitCommandExecutor());
    }
}

