package duke.command;

import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit from Duke.
 */
public class ExitCommand implements Command {

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
