package duke.command;

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
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Message to notify that the list has been sorted.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        return ui.sortMessage();
    }
}
