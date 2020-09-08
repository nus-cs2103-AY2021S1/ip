package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to sort tasks according to their type and date-time.
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        manager.sortTasks();
        storage.saveTasks(manager.getTasks());
        return ui.showSortMessage(manager.getTasks());
    }
}
