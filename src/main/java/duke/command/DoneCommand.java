package duke.command;

import duke.task.Task;
import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;


public class DoneCommand implements Command {
    private final int targetIndex;

    public DoneCommand(int index) {
        this.targetIndex = index;
    }

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        Task markedTask = tasks.markDone(targetIndex);
        ui.showDoneGreet(markedTask);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
