package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

/**
 * Lists all tasks in Bob's tasklist.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExited() {
        return false;
    }

    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showToUser(tasks.toString());
    }

}
