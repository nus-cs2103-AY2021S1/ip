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
    public String execute(TaskList tasklist, Storage storage) {
        return Ui.showFarewell();
    }
}
