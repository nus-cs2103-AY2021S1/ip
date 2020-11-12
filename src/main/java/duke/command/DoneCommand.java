package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * <p>duke.command.DoneCommand class defines the behaviour of a command to mark a task as done.</p>
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        assert (taskIndex >= 1) : "duke.task.Task index must be more than 0";
        ui.replyDone(taskManager.getTask(taskIndex - 1));
        taskManager.markTaskAsDone(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
