package dukechatbot.command;

import dukechatbot.executor.FindCommandExecutor;
import dukechatbot.parser.CommandParser;

/**
 * Represents the command for find. 
 * Stores the argument that contains the 
 * search keyword.
 */
public class FindCommand extends Command {

    private final String argument;

    public FindCommand(String input) {
        super(input, new FindCommandExecutor());
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
