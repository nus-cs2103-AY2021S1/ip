package command;

import executor.DoneCommandExecutor;
import parser.CommandParser;

public class DoneCommand extends Command {

    private final String argument;

    public DoneCommand(String input) {
        super(input, new DoneCommandExecutor());
        this.argument = CommandParser.getTitle(input);
    }

    public String getArgument() {
        return argument;
    }
}
