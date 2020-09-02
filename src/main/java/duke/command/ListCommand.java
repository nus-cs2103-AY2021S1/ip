package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand deals with list input.
 */
public class ListCommand extends Command {
    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return Ui.show(tasklist.toString());
    }
}
