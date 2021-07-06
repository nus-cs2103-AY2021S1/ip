package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    /**
     * Shows the itemized list of tasks to the user.
     *
     * @param tasks current list of tasks to be itemized and shown
     * @param ui user interface to show messages
     * @param storage storage interface to write the current list of tasks in
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.isEmpty()) {
            return ui.format("There are no items in the list right now.");
        } else {
           return ui.format(tasks.itemize());
        }
    }
}
