package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

/**
 * Represents a Help Command by the user.
 * It prints out an FAQ to the user using <code>UIManager</code>
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    /**
     * Prints FAQ for the user.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {
        uiManager.printDukeInstructions();
    }
}
