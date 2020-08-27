package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
import duke.task.DukeTask;

import java.util.ArrayList;

/**
 * Represents a Find Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>String</code> keyword
 * to be found from the <code>TaskList</code>.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Searches for <code>DukeTask</code> with the keyword in the <code>TaskList</code> and prints feedback.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {

        ArrayList<DukeTask> filteredList = new ArrayList<>();
        for (DukeTask task : taskList.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                filteredList.add(task);
            }
        }

        if (filteredList.size() == 0) {
            uiManager.printFindCannotBeFound(keyword);
        } else {
            uiManager.printFindFilteredList(keyword, filteredList.size() > 1);
            for (int i = 0; i < filteredList.size(); i++) {
                uiManager.printNumberedTask(filteredList.get(i), i);
            }
        }
    }
}

