package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks in the current task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui object to output messages.
     * @param storage object to write TaskList to file.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.displayTasks(tasks);
    }
}
