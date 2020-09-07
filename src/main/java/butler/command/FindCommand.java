package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to find tasks with specific words.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a command to find tasks with the given <code>keyword</code>.
     *
     * @param keyword Keyword within tasks to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks within the given <code>taskList</code> that have the <code>keyword</code>.
     * Alerts users about the tasks that have been found using <code>ui</code>.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui       User interface to interact with user.
     * @param storage  Storage which stores given <code>taskList</code> on hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTasks = taskList.findTasks(keyword);
        ui.printFilteredTaskList(filteredTasks);
    }

    /**
     * Returns a boolean whether this command is an <code>ExitCommand</code> or not.
     *
     * @return <code>false</code>
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
