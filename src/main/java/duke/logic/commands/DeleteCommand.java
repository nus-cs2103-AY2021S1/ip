package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles 'delete' commands input by user.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand class
     *
     * @param command String input by user.
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Extracts the index of the task to be deleted and deletes the appropriate Task from TaskManager.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @throws DukeException If command is not properly formatted.
     */
    @Override
    public String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        // Handles empty delete command
        if (commandDetails.length == 1) {
            throw new DukeException("Delete Index not provided!");
        }

        try {
            int delIndex = Integer.parseInt(commandDetails[1]) - 1;
            Task delTask = tm.getTask(delIndex);
            tm.deleteTask(delIndex);
            postCommandSave(tm, storage);
            return ("Task deleted: " + delTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Index is not a number!");
        }
    }
}
