package duke.logic.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles 'find' command input by user.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand class.
     *
     * @param command String input by user.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Gets Tasks containing keyword from TaskManager.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @throws DukeException If search term is not provided.
     */
    @Override
    public String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);

        // Handles empty find command
        if (commandDetails.length == 1) {
            throw new DukeException("Search term not provided!");
        }

        assert commandDetails.length == 2;
        String searchTerm = commandDetails[1];
        ArrayList<Task> mathingTasks = tm.findTasks(searchTerm);
        if (mathingTasks.isEmpty()) {
            String s = "No matching tasks found!";
            return s;
        } else {
            String s = "I found the following matching tasks:\n";
            s = s + ui.buildTaskList(mathingTasks);
            return s;
        }
    }
}
