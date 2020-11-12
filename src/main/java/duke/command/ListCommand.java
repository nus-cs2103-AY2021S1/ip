package duke.command;

import duke.backend.Storage;
import duke.response.Response;
import duke.task.TaskList;

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
    public String execute(TaskList tasks, Response ui, Storage storage) {
        return ui.showList(tasks);
    }
}
