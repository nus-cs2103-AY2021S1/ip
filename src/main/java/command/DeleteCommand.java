package command;

import executor.DeleteCommandExecutor;
import parser.CommandParser;

public class DeleteCommand extends Command {

    private final String argument;

    public DeleteCommand(String input) {
        super(input, new DeleteCommandExecutor());
        this.argument = CommandParser.getTitle(input);
    }

    public String getArgument() {
        return argument;
    }
}
