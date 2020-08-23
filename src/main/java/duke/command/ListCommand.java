package duke.command;

import duke.storage.*;
import duke.ui.Ui;

/**
 * Encapsulates a Command which lists out all the tasks in the list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCurrentTasks(tasks.getTaskList());
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
