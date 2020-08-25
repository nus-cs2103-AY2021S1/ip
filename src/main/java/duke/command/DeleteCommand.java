package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index);
    }
}
