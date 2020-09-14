package duke.command;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Ui;

/**
 * Represents the Listing of all Tasks action.
 */
public class ListCommand implements Command {
    /**
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Performs the printing of list of Tasks in TaskList.
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
