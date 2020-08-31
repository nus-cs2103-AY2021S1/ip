package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand implements Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.displayTaskDelete(deletedTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
