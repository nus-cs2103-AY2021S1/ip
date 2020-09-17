package mattbot.Command;

import mattbot.Errors.ErrorExceptions;
import mattbot.Tasks.TaskManager;
import mattbot.Tasks.task;
import mattbot.UI.UserInterface;

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
        task t;
        int index = i;
        try {
            t = TaskManager.getTask(index);
            TaskManager.completed(t);
            UserInterface.done2();
            return "    " + TaskManager.read(t) + System.lineSeparator()
                    + "The tracked Tasks.task has been marked as completed! Congrats~~!";
        } catch (IndexOutOfBoundsException e) {
            throw new ErrorExceptions("There is no such Tasks.task!");
        }
    }
}
