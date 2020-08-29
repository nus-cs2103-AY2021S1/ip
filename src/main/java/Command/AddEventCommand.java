package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.*;

/**
 * Represents a command that adds an Event task.
 */
public class AddEventCommand extends Command {

    /**
     * Creates and add a new Event into the list of tasks.
     *
     * @param input user input.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input,2);
        TaskManager.newTask(name,"Event",date, getFileDir());
    }
}
