package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a Command which lists out all the tasks in the list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCurrentTasks(tasks.getTaskList());
    }

}
