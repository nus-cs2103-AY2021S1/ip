package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.Task;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to add a <code>Task</code> to the current list of tasks.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs a command to add the given <code>task</code>.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the current list of tasks.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the task has been successfully added.
     *
     * @param taskListManager Manager of the list of tasks in which task is to be added to.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) {
        TaskList newTaskList = taskListManager.getCurrentTaskList().copy();
        newTaskList.addTask(task);
        taskListManager.addLatestTaskList(newTaskList);
        storage.storeTaskList(newTaskList);
        return ui.showTaskIsAdded(task);
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

    /**
     * Returns the task to be added.
     *
     * @return Task to be added by this command.
     */
    public Task getTask() {
        return task;
    }
}
