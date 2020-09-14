package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to find tasks with specific words.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a command to find tasks with the given <code>keyword</code> and
     * updates the list of tasks saved in the hard disk.
     *
     * @param keyword Search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks within the given <code>taskList</code> that have the <code>keyword</code>.
     * Alerts users via <code>ui</code> about the tasks that have been found.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error with saving the list of tasks occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        TaskList filteredTasks = taskList.findTasks(keyword);
        storage.storeTaskList(taskList);
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
