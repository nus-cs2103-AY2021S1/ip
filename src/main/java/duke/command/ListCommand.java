package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Listing of all Tasks action.
 */
public class ListCommand implements Command {

    /**
     * Performs the printing of list of Tasks in TaskList.
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
        return true;
    }
}
