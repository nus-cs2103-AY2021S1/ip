package command;

import executor.DoneCommandExecutor;
import parser.CommandParser;

/**
 * Represents the command for done. 
 * Stores the argument that contains the 
 * task item number to mark done.
 */
public class DoneCommand extends Command {

    private final String argument;

    public DoneCommand(String input) {
        super(input, new DoneCommandExecutor());
        this.argument = CommandParser.getTitle(input);
    }

    /**
     * Returns the String argument attribute of this object. 
     *
     * @return argument attribute.
     */
    public String getArgument() {
        return argument;
    }
}
