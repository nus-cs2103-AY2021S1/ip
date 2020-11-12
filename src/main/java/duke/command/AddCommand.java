package duke.command;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * <p>duke.command.AddCommand class defines the behaviour of a command to add a task.</p>
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        taskManager.addTask(task);
        ui.replyAdd(task, taskManager);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
