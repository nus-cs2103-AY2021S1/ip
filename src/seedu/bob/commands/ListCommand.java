package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;

import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

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
