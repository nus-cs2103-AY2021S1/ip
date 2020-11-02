package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents a command to find tasks with specific words.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a command to find tasks with the given <code>keyword</code>.
     *
     * @param keyword Search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks within the current list of tasks that have the <code>keyword</code>.
     * Alerts the user about the tasks that have been found
     * and updates the task list saved in the hard disk.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     */
    @Override
    public String execute(TaskListManager taskListManager, Ui ui, Storage storage) {
        TaskList filteredTasks = taskListManager.getCurrentTaskList().findTasks(keyword);
        storage.storeTaskList(taskListManager.getCurrentTaskList());
        return ui.showFilteredTaskList(filteredTasks);
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
