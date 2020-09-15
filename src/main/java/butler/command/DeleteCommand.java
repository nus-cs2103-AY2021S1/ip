package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to delete a <code>Task</code> from the current list of tasks.
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
     * Deletes the task specified by <code>taskIndex</code> from the current list of tasks.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the task has been successfully deleted.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if <code>taskIndex</code> is an invalid index.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) throws ButlerException {
        TaskList newTaskList = taskListManager.getCurrentTaskList().copy();
        newTaskList.deleteTask(taskIndex);
        taskListManager.addLatestTaskList(newTaskList);
        storage.storeTaskList(newTaskList);
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
