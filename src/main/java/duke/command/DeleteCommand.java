package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Task;

/**
 * Represents a command which is used to delete a task from the list of tasks.
 * It is executed when the execute method is called.
 */
public class DeleteCommand extends Command{

    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     *  Executes the delete command.
     *  It removes the task specified when the command was created.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.deleteTask(index);
        ui.showDelete(removed, tasks.numTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
