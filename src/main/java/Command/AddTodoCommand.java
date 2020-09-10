package Command;

import static Parser.InputManager.getFileDir;
import static Parser.InputManager.getName;

import Errors.ErrorExceptions;
import Tasks.TaskManager;



/**
 * Represents a command that adds a Todo task.
 */
public class AddTodoCommand extends Command {

    /**
     * Creates and add a new Todo into the list of tasks.
     * Returns the added Todo task message.
     *
     * @param input user input.
     * @return String Todo message.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static String execute2(String input) throws ErrorExceptions {
        String name = getName(input, 1);
        return TaskManager.newTask2(name, "Todo", null, getFileDir());
    }
}
