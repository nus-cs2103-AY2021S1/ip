package Parser;

import Command.ShowFunctionCommand;

/**
 * Represents the bridge that calls the appropriate show all commands command.
 */
public class ParseShow extends Parse {

    /**
     * Calls the show command.
     */
    public static void execute(){
        ShowFunctionCommand.execute();
    }
}
