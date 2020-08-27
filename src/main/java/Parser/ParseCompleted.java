package Parser;

import Command.CompletedCommand;
import Errors.ErrorExceptions;

/**
 * Represents the bridge that calls the appropriate complete task command.
 */
public class ParseCompleted extends Parse{

    /**
     * Calls the completion command.
     *
     * @param i index of the task.
     * @throws ErrorExceptions when the task cannot be found.
     */
    public static void execute(int i) throws ErrorExceptions{
        CompletedCommand.execute(i);
    }
}
