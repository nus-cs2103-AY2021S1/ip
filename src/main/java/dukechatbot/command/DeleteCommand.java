package dukechatbot.command;

import dukechatbot.executor.DeleteCommandExecutor;
import dukechatbot.parser.CommandParser;

/**
 * Represents the command for delete.
 * Stores the argument that contains the task item number to be deleted.
 */
public class DeleteCommand extends Command {

    private final String argument;

    public DeleteCommand(String input) throws IndexOutOfBoundsException {
        super(input, new DeleteCommandExecutor());
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
