package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeSaveDataException;

/**
 * Represents a command by the user to exit Duke.
 */
public class ByeCommand extends Command {

    /**
     * Creates a <code>ByeCommand</code> that signals software loop termination.
     */
    public ByeCommand() {
        super();
        this.isByeCommand = true;
    }

    /**
     * Executes steps required to exit the app safely.
     * Displays a farewell message and attempts to save data to save file.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        // Display goodbye message to user
        ui.displayGoodbye();

        // Attempts to save data to save file
        try {
            saveManager.save(taskManager);

        } catch (DukeSaveDataException e) {
            ui.displayException(e);
        }

    }

}
