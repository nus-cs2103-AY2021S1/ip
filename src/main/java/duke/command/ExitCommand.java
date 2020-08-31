package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

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
