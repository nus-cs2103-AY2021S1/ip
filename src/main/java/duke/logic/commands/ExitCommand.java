package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles 'bye' command input by user.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand class.
     *
     * @param command String input by user.
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Saves the current Tasks to disk and shows exit message.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @return Goodbye message.
     * @throws DukeException If command is not properly formatted.
     */
    @Override
    public String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);

        // Handles exit command with extra parameters
        if (commandDetails.length != 1) {
            throw new DukeException("Exit command should not include extra parameters!");
        }

        // Save current tasks to disk before exiting
        postCommandSave(tm, storage);
        return ui.showGoodbye();
    }

    /**
     * Overrides the abstract class to send exit signal to Duke.
     *
     * @return Exit signal so Duke terminates after command is processed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
