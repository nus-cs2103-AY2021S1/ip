package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskListManager;

/**
 * Represents a command to list all tasks in a task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command to list all tasks in a task list.
     */
    public ListCommand() {
    }

    /**
     * Lists out the tasks in the current list of tasks using <code>ui</code> and
     * updates the list of tasks saved in the hard disk.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) {
        storage.storeTaskList(taskListManager.getCurrentTaskList());
        return ui.showTaskList(taskListManager.getCurrentTaskList());
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
