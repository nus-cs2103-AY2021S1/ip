package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
