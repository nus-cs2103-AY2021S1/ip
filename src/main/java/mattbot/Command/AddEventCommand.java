package mattbot.Command;

import static mattbot.Parser.InputManager.getDate;
import static mattbot.Parser.InputManager.getFileDir;
import static mattbot.Parser.InputManager.getName;

import mattbot.Errors.ErrorExceptions;
import mattbot.Tasks.TaskManager;



/**
 * Represents a command that adds an Event task.
 */
public class AddEventCommand extends Command {

    /**
     * Creates and add a new Event into the list of tasks.
     * Returns the added Event task message.
     *
     * @param input user input.
     * @return String event message.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static String execute2(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input, 2);
        return TaskManager.newTask2(name, "Event", date, getFileDir());
    }
}
