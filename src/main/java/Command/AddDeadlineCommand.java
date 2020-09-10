package Command;

import static Parser.InputManager.getDate;
import static Parser.InputManager.getFileDir;
import static Parser.InputManager.getName;

import Errors.ErrorExceptions;
import Tasks.TaskManager;


/**
 * Represents a command that adds a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Creates and add a new deadline into the list of tasks.
     * Returns the added Deadline task message.
     *
     * @param input user input.
     * @return String deadline nessage.
     * @throws ErrorExceptions failed to get name or date of task.
     */
    public static String execute2(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input, 1);
        return TaskManager.newTask2(name, "Deadline", date, getFileDir());
    }
}
