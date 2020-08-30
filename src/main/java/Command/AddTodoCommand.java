package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.getFileDir;
import static Parser.InputManager.getName;

/**
 * Represents a command that adds a Todo task.
 */
public class AddTodoCommand extends Command {

    /**
     * Creates and add a new Todo into the list of tasks.
     *
     * @param input user input.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 1);
        TaskManager.newTask(name,"Todo",null, getFileDir());
    }
}
