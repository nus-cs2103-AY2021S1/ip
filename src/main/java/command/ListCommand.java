package command;

import executor.ListCommandExecutor;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input, new ListCommandExecutor());
    }
}
