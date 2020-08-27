package Parser;

import Command.ListCommand;
import Errors.ErrorExceptions;
import Tasks.TaskManager;

/**
 * Represents the bridge that calls the appropriate list task command.
 */
public class ParseList extends Parse{

    /**
     * Calls the list command.
     */
    public static void execute(){
        ListCommand.execute();
    }
}
