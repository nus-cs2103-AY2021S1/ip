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
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (n < 1 || n > taskList.size()) {
            throw new InvalidRangeError();
        }
        Task task = taskList.get(n - 1);
        taskList.remove(task);
        return ui.deleteTask(task.toString(), taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
