package duke.command;

import duke.task.TaskList;

import duke.Ui;
import duke.Storage;

/**
 * Represents a command to terminate the DukeBot session.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the DukeBot session.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // storage.saveFile(tasks);
        ui.showFarewell();
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return True since the DukeBot session has been terminated by the user.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
