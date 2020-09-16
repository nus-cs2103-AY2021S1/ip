package duke.command;

import duke.error.DukeError;
import duke.error.InvalidRangeError;
import duke.error.TaskAlreadyCompletedError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

public class DoneTaskCommand implements Command {
    private final int n;

    public DoneTaskCommand(int n) {
        this.n = n;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (n < 1 || n > taskList.size()) {
            throw new InvalidRangeError();
        }
        Task task = taskList.get(n - 1);
        if (task.getIsDone()) {
            throw new TaskAlreadyCompletedError(task);
        }
        task.markAsDone();
        return ui.doneTask(task.getDescription());

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
