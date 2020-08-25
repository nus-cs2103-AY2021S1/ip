package duke.command;

import duke.DukeException;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.isEmpty()) {
            ui.print("There are no items in the list right now.");
        } else {
           ui.print(tasks.itemize());
        }
    }
}
