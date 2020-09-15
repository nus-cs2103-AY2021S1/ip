package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {}

    /**
     * Prints out the entire list of tasks.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String listItems = "";
        for (int i = 1; i <= taskList.size(); i++) {
            listItems = listItems + i + ". " + taskList.get(i - 1) + "\n";
        }
        String repsonse = "Here are the tasks in your list: \n" + listItems;
        return repsonse;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
