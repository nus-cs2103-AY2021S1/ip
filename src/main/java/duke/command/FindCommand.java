package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command where user queries tasks containing a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Instantiates a FindCommand with the queried keyword.
     * @param keyword the keyword queried
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by querying the task from taskList and displaying
     * the queried tasks.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage updating on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasksWithWord(keyword);
        return ui.listQueriedTasks(tasks);
    }
}
