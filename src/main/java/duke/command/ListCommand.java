package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a user command to display all <code>Task</code>s stored in Duke.
 */
public class ListCommand extends Command {

    /**
     * Displays all <code>Task</code>s stores in <code>taskManager</code>.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        ui.display(taskManager.toString());
    }

}
