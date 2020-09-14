package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to delete a <code>Task</code> from a list of tasks.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a command to delete a task with specified <code>taskIndex</code>.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task specified by <code>taskIndex</code> from <code>taskList</code>.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the task has been successfully deleted.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if <code>taskIndex</code> is an invalid index
     *                         or an error occurs in saving the list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        taskList.deleteTask(taskIndex);
        storage.storeTaskList(taskList);
        return ui.showTaskIsDeleted(taskIndex);
    }

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
