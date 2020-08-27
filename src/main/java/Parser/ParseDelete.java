package Parser;

import Command.DeleteCommand;
import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;
import UI.UserInterface;
import java.util.NoSuchElementException;

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
