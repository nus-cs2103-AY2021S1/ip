package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to find a Task in TaskList by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand with a given keyword.
     *
     * @param keyword Keyword to be searched against the Tasks.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Find Tasks containing the given keyword.
     * Displays list of Tasks found to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of Tasks.
     * @return Ui message to display the list of Tasks found.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        TaskList keywordTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(keyword)) {
                keywordTasks.add(task);
            }
        }
        return ui.foundItems(keywordTasks, this.keyword);
    }

}
