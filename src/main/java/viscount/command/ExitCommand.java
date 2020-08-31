package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
