package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        ui.displayTasks(manager.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}