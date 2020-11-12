package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * <p>duke.command.DeleteCommand class defines the behaviour of a command to delete an item in the task list.</p>
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskNum) {
        this.taskIndex = taskNum;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        assert (taskIndex >= 1) : "duke.task.Task index must be more than 0";
        ui.replyDelete(taskManager.getTask(taskIndex - 1));
        taskManager.removeTask(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
