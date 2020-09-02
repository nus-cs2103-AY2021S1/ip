package command;

import executor.ListCommandExecutor;

/**
 * Represents the command for done. 
 * Stores the argument that contains the 
 * task item number to mark done.
 */
public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input, new ListCommandExecutor());
    }
}
