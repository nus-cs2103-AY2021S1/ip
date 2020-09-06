package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Handles 'list' command input by user.
 */
public class ListCommand extends Command{

    /**
     * Constructor for ListCommand class.
     *
     * @param command String input by user.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Loads Tasks in TaskManager and prints the entire list.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @throws DukeException If command is not properly formatted.
     */
    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        if (commandDetails.length != 1) {
            throw new DukeException("List command should not include extra parameters!");
        }
        ArrayList<Task> taskList = tm.getTaskList();
        String s = "";
        if (taskList.isEmpty()) {
            s = "List is empty!";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                s = s + (i + 1) + ". " + taskList.get(i) + "\n";
            }
        }
        ui.showDetails(s);
    }
}
