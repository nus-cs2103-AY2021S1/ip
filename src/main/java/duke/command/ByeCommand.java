package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements bye command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class ByeCommand extends Command {
    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses in to be passed to user.
     * @throws DukeException If failed to save tasks.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks.getTasks());
        return ui.showGoodbyeUser();
    }

    /**
     * Returns an indicator if the program will terminate.
     *
     * @return Indicator of termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
