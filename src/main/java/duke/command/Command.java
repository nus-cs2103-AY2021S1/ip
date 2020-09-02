package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements command objects.
 *
 * @author Audrey Felicio Anwar
 */
public abstract class Command {
    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses in to be passed to user.
     * @throws DukeException If there is an error.
     */
    public abstract String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns an indicator if the program will terminate.
     *
     * @return Indicator of termination.
     */
    public boolean isExit() {
        return false;
    }
}
