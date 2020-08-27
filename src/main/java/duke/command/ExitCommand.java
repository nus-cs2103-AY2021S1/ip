package duke.command;

import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

import java.io.IOException;

/**
 * Represents an Exit Command by the user.
 * It saves the <code>DukeTask</code> inside its <code>TaskList</code>
 * before terminating the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Saves user's <code>DukeTask</code> from the <code>TaskList</code> and prints feedback.
     * Saving of data is executed by <code>StorageManager</code>.
     *
     * @param taskList <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @throws IOException  If saving data fails.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws IOException {
        storageManager.saveData(taskList.getTaskList());
    }
}
