package duke.command;

import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to exit the programme.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
