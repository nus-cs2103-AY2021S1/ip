package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.Task;
import butler.task.TaskList;

/**
 * Represents a command to add a <code>Task</code> to a <code>TaskList</code>.
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
     * Adds the task to the specified <code>taskList</code>.
     * Updates the list of tasks saved in the hard disk and
     * alerts the user that the task has been successfully added.
     *
     * @param taskList List of tasks in which task is to be added to.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error with saving the list of tasks occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        taskList.addTask(task);
        storage.storeTaskList(taskList);
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
