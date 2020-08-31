package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class MarkDoneCommand implements Command {
    private final int taskNumber;

    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task completedTask = tasks.getTask(taskNumber);
        completedTask.markDone();
        ui.displayTaskComplete(completedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
