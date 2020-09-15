package duke.command;

import duke.exception.FileUpdateFailException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstracts the sorting of tasks in the {@code TaskList}.
 */
public class SortCommand extends Command {

    /**
     * Executes the sorting of tasks in the {@code TaskList}.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Message to notify that the list has been sorted.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FileUpdateFailException {
        taskList.sort();
        storage.updateFile(taskList);
        return ui.sortMessage();
    }
}
