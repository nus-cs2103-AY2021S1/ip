package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Represents a command to view a list of Duke's function and the function commands.
 */
public class FunctionsCommand extends Command {

    /**
     * Prints all of Duke's functions and the commands for each function.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of all of Duke's functions and commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printFunctions();
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
