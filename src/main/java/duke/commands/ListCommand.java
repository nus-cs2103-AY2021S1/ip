package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Lists all tasks from the task list.
 */
public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAllTasks(taskList);
    }
}


