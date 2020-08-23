package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the ListCommand to list all the tasks on task list.
 */
public class ListCommand extends Command {
    /**
     * Returns false since ListCommand is not an ExitCommand.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes ListCommand to list the tasks.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException If task list has no tasks.
     */
    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!");
        } else {
            taskList.listTasks();
        }
    }
}
