package Duke.command;

import Duke.task.Task;
import Duke.task.TaskList;
import Duke.utils.Ui;

public class DoneCommand extends Command {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = tasks.get(index);
        tasks.markAsDone(index);
        ui.showDoneMessage(task);
    }
}
