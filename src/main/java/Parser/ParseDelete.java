package Parser;

import Command.DeleteCommand;
import Errors.ErrorExceptions;


/**
 * Represents the bridge that calls the appropriate delete task command.
 */
public class ParseDelete extends Parse {

    /**
     * Calls the delete task command.
     * @param i task index.
     * @throws ErrorExceptions when task cannot be found.
     */
    public static void execute(int i) throws ErrorExceptions {
        DeleteCommand.execute(i);
    }
}
