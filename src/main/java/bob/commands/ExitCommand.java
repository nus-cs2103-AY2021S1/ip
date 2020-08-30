package bob.commands;

import bob.data.task.Tasklist;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Exits and cease Bob.
 */
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
