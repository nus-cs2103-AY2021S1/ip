package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to terminate and exit from the Duke session.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the Duke session.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply to indicate the exit from the Duke session.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFarewell();
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return True since the Duke session has been terminated by the user.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
