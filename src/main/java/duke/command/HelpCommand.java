package duke.command;

import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;

/**
 * Represents a Help Command by the user.
 * It prints out an FAQ to the user using <code>UIManager</code>
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Prints FAQ for the user.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask) {
        if (isGuiTask) {
            response = uiManager.getDukeInstructions();
        } else {
            uiManager.printDukeInstructions();
        }
    }
}
