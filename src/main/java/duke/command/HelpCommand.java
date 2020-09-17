package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to view a list of Duke's function and the function commands.
 */
public class HelpCommand extends Command {

    /**
     * Returns a string containing all of Duke's functions and the commands for each function.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of all of Duke's functions and commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFunctions();
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
