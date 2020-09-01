package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand deals with bye input.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExited() {
        return true;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
