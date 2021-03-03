package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to filter tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints tasks that match the search keyword.
     * @param taskList
     * @param ui
     * @param storage
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String listItems = "";
        for (int i = 1; i <= taskList.size(); i++) {
            if (taskList.get(i - 1).toString().contains(keyword)) {
                listItems = listItems + "\n";
                listItems = listItems + i + ". " + taskList.get(i - 1);
            }
        }
        String response = "Here are your tasks that matches your search:" + listItems;
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
