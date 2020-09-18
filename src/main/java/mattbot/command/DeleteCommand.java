package mattbot.command;

import java.util.NoSuchElementException;

import mattbot.errors.ErrorExceptions;
import mattbot.tasks.Task;
import mattbot.tasks.TaskManager;
import mattbot.ui.UserInterface;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends Command {

    /**
     * Deletes the selected task from the list.
     * Returns the deleted task message.
     *
     * @param i task index.
     * @throws ErrorExceptions failed to find task.
     */
    public static String execute2(int i) throws ErrorExceptions {
        Task t;
        int index = i;
        try {
            t = TaskManager.getTask(index);
            TaskManager.delete(index);
            UserInterface.done2();
            return "    " + TaskManager.read(t) + System.lineSeparator()
                    + "The tracked tasks.task has been deleted!";
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("There is no suck tasks.task!");
        }
    }
}
