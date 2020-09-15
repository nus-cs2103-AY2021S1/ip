package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles 'done' command input by user.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand class.
     *
     * @param command String input by user.
     */
    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Extracts the index of the task to be marked as done and marks the appropriate Task on TaskManager.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @throws DukeException If command is not properly formatted.
     */
    @Override
    public String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        if (commandDetails.length == 1) {
            throw new DukeException("Done Index not provided!");
        }
        try {
            int doneIndex = Integer.parseInt(commandDetails[1]) - 1;
            Task doneTask = tm.getTask(doneIndex);
            tm.markTaskDone(doneIndex);
            return ("Task marked as done: " + doneTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Index is not a number!");
        }
    }
}
