package duke.command;

import duke.exception.DukeIoException;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;


/**
 * Represents an Exit Command by the user.
 * It saves the <code>DukeTask</code> inside its <code>TaskList</code>
 * before terminating the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public boolean getExitStatus() {
        return true;
    }

    /**
     * Saves user's <code>DukeTask</code> from the <code>TaskList</code> and prints feedback.
     * Saving of data is executed by <code>StorageManager</code>.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     * @throws DukeIoException If saving data fails.
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask)
            throws DukeIoException {
        storageManager.saveData(taskList.getTaskList());
    }
}
