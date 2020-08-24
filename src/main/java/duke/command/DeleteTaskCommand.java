package duke.command;

import duke.error.DukeError;
import duke.error.InvalidRangeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

public class DeleteTaskCommand implements Command {
    private final int n;

    public DeleteTaskCommand(int n) {
        this.n = n;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (n < 1 || n > taskList.numberOfTasks()) {
            throw new InvalidRangeError();
        }
        Task task = taskList.getTaskList().get(n - 1);
        taskList.deleteTask(task);
        ui.deleteTask(task.toString(), taskList.numberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
