package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to be executed by Duke.
 */
public abstract class Command {

    /**
     * Executes the relevant command.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply to the user after execution of the relevant command.
     * @throws DukeException If the command could not be executed successfully.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the Duke session has ended.
     *
     * @return True if the Duke session is going to be terminated, false otherwise.
     */
    public abstract boolean isExit();

}
