package duke.command;


import duke.task.Task;
import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete element with certain index in the task list.
 */
public class DeleteCommand implements Command {

    /** Index of the target task to be deleted */
    private final int targetIndex;

    /**
     * Creates a delete command to delete specific task in the list.
     *
     * @param index Index of task that will be deleted.
     */
    public DeleteCommand(int index) {
        this.targetIndex = index;
    }

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.delete(targetIndex);
        ui.showDeletionNotification(tasks,deletedTask);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
