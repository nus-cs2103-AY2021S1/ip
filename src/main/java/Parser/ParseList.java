package Parser;

import Command.ListCommand;
import Errors.ErrorExceptions;
import Tasks.TaskManager;

public class ParseList extends Parse{
    public static void execute(){
        ListCommand.execute();
    }
}
