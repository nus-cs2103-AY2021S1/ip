package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command to be executed by the DukeBot.
 */
public abstract class Command {

    /**
     * Executes the relavant command.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If the command cannot be executed successfully.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return True if the DukeBot session is going to be terminated, false otherwise.
     */
    public abstract boolean isExit();

}
