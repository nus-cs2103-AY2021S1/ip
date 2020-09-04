package duke.command;

import java.util.ArrayList;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UiManager;
import duke.task.DukeTask;


/**
 * Represents a Find Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>String</code> keyword
 * to be found from the <code>TaskList</code>.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public boolean getExitStatus() {
        return true;
    }

    /**
     * Searches for <code>DukeTask</code> with the keyword in the <code>TaskList</code> and prints feedback.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task.
     */
    @Override
    public void execute(TaskList taskList, UiManager uiManager, StorageManager storageManager, boolean isGuiTask) {

        ArrayList<DukeTask> filteredList = new ArrayList<>();
        for (DukeTask task : taskList.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                filteredList.add(task);
            }
        }

        if (filteredList.size() == 0) {
            if (isGuiTask) {
                response = uiManager.getFindCannotBeFound(keyword);
            } else {
                uiManager.printFindCannotBeFound(keyword);
            }
        } else {
            if (isGuiTask) {
                StringBuilder output = new StringBuilder(
                        uiManager.getFindFilteredList(keyword, filteredList.size() > 1) + "\n");
                for (int i = 0; i < filteredList.size(); i++) {
                    output.append(uiManager.getNumberedTask(filteredList.get(i), i));
                    output.append("\n");
                }
                response = output.toString();
            } else {
                uiManager.printFindFilteredList(keyword, filteredList.size() > 1);
                for (int i = 0; i < filteredList.size(); i++) {
                    uiManager.printNumberedTask(filteredList.get(i), i);
                }
            }
        }
    }
}

