package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Task;

public class DeleteCommand extends Command{

    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.deleteTask(index);
        ui.showDelete(removed, tasks.numTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
