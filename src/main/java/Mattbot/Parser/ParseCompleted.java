package Mattbot.Parser;

import Mattbot.Command.CompletedCommand;
import Mattbot.Errors.ErrorExceptions;

/**
 * Represents the bridge that calls the appropriate complete task command.
 */
public class ParseCompleted extends Parse {

    /**
     * Calls the completion command.
     * Returns the completion message.
     *
     * @param i index of the task.
     * @return String completion message.
     * @throws ErrorExceptions when the task cannot be found.
     */
    public static String execute2(int i) throws ErrorExceptions {
        return CompletedCommand.execute2(i);
    }
}
