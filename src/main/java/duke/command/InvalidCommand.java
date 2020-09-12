package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.task.TaskList;

/**
 * Represents a command to handle invalid inputs by the user.
 */
public class InvalidCommand extends Command {

    /**
     * Throws a DukeException to inform the user of the invalid input provided.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply to an invalid input by the user.
     * @throws DukeException DukeException is thrown to inform the user of the invalid input provided.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String error = "Invalid Function!";
        throw new InvalidFunctionException(error);
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
