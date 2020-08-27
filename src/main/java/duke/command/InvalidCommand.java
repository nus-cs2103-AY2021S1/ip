package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command to handle invalid inputs by the user.
 */
public class InvalidCommand extends Command {

    /**
     * Throws a DukeException to inform the user of the invalid input provided.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException DukeException is thrown to inform the user of the invalid input provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String err = "Invalid Function! Input '/commands' for a list of all my commands.";
        throw new InvalidFunctionException(err);
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
