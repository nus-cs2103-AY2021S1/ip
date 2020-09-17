package Mattbot.Parser;

import Mattbot.Command.DeleteCommand;
import Mattbot.Errors.ErrorExceptions;


/**
 * Represents the bridge that calls the appropriate delete task command.
 */
public class ParseDelete extends Parse {

    /**
     * Calls the delete task command.
     * Returns the deletion message.
     *
     * @param i task index.
     * @return String deletion of task message.
     * @throws ErrorExceptions when task cannot be found.
     */
    public static String execute2(int i) throws ErrorExceptions {
        return DeleteCommand.execute2(i);
    }
}
