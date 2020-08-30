package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a Command given by the user to list all Tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints all lists in the TaskList.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        Ui.listMessage(list.getTaskList());
    }
}
