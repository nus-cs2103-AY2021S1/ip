package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.*;

/**
 * Represents a command that adds a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Creates and add a new deadline into the list of tasks.
     * @param input user input.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input,1);
        TaskManager.newTask(name,"Deadline",date, getFileDir());
    }
}
