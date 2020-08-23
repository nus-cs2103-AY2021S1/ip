package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExited() {
        return true;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}
