package bob.commands;

import bob.data.task.Tasklist;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Lists all tasks in Bob's tasklist.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showToUser(tasks.toString());
    }

}
