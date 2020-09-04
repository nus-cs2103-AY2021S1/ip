package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidListCommandException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the ListCommand to list all the tasks on task list. */
public class ListCommand extends Command {
    /**
     * Returns false since ListCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes ListCommand to list the tasks.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of list of tasks.
     * @throws FocusException If task list has no tasks.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        if (taskList.getSize() == 0) {
            throw new InvalidListCommandException();
        } else {
            return taskList.listTasks();
        }
    }
}
