package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskListManager;

/**
 * Represents a command to exit Butler.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a command to exit Butler.
     */
    public ExitCommand() {
    }

    /**
     * Alerts the user that Butler will be closed and updates the task list saved in the hard disk.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) {
        storage.storeTaskList(taskListManager.getCurrentTaskList());
        return ui.showFarewellMessage();
    }

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>true</code>.
     */
    @Override
    public Boolean isExit() {
        return true;
    }
}
