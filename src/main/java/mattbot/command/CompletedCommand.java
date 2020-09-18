package mattbot.command;

import mattbot.errors.ErrorExceptions;
import mattbot.tasks.Task;
import mattbot.tasks.TaskManager;
import mattbot.ui.UserInterface;

/**
 * Represents a command that marks a task as completed.
 */
public class CompletedCommand extends Command {
    /**
     * Marks the selected task as completed.
     * Returns the completion message.
     *
     * @param i task index.
     * @return String completion message.
     * @throws ErrorExceptions failed to locate specified task.
     */
    public static String execute2(int i) throws ErrorExceptions {
        Task t;
        int index = i;
        try {
            t = TaskManager.getTask(index);
            TaskManager.completed(t);
            UserInterface.done2();
            return "    " + TaskManager.read(t) + System.lineSeparator()
                    + "The tracked tasks.task has been marked as completed! Congrats~~!";
        } catch (IndexOutOfBoundsException e) {
            throw new ErrorExceptions("There is no such tasks.task!");
        }
    }
}
