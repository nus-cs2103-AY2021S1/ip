package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks in the current task list.
 */
public class ListCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException {
        Ui.displayTasks(tasks.getList());
    }
}
