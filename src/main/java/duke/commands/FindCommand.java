package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;
import duke.tasklist.tasklistfilter.SearchFilter;
import duke.tasklist.tasklistfilter.TaskListFilter;

/**
 * Handles the filtering of tasks using a keyword in the chatbot.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * FindCommand constructor.
     *
     * @param keyword The keyword to filter the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the filtered list of tasks with the keyword.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The find message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskListFilter searchFilter = new SearchFilter(keyword);
        return ui.filteredList(tasks.findTasks(searchFilter));
    }
}
