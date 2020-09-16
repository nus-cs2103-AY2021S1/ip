package dude.command;

import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command gets the ui class to list all of the current tasks.
 */

public class ListCommand extends Command {

    /**
     * Constructor for ListCommand class.
     *
     * @param action action the command is to perform.
     */
    public ListCommand(String action) {
        super(action);
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder uiOutput = new StringBuilder();
        uiOutput.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getCount(); i++) {
            uiOutput.append(String.valueOf(i + 1) + "." + tasks.getTask(i).toString() + "\n");
        }
        ui.setMessage(uiOutput.toString());
    }
}
