package mattbot.command;

import static mattbot.parser.InputManager.getFileDir;
import static mattbot.parser.InputManager.getName;

import mattbot.errors.ErrorExceptions;
import mattbot.tasks.TaskManager;



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
