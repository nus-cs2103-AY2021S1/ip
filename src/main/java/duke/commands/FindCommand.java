package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the filtering of tasks using a keyword in the chatbot.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * FindCommand constructor.
     * @param keyword The keyword to filter the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the filtered list of tasks with the keyword.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to indicate that it is not the terminating command.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.filteredList(tasks.findTasks(keyword));
        return false;
    }
}
