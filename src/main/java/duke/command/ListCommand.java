package duke.command;

import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return ui.displayTasks(manager.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
