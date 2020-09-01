package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the exit command
 */
public class ExitCommand extends Command {

    /**
     * Shows the user the goodbye message
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
