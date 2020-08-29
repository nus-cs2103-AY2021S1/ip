package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UiManager;

/**
 * Represents a List Command by the user.
 * It prints out the list of user's <code>DukeTask</code>
 * to the user using <code>UIManager</code>
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    /**
     * Prints all <code>DukeTask</code> for the user.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     */
    @Override
    public void execute(TaskList taskList, UiManager uiManager, StorageManager storageManager) {
        for (int i = 0; i < taskList.getSize(); i++) {
            uiManager.printNumberedTask(taskList.getTaskList().get(i), i);
        }
        uiManager.printTaskStatus(taskList.getSize());
    }
}
