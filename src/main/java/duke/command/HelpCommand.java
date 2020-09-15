package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * HelpCommand deals with help input.
 */
public class HelpCommand extends Command {
    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return Ui.showHelp();
    }
}
