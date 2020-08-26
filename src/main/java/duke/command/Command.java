package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command that is being received by Duke.
 */
public abstract class Command {

    /**
     * Executes the given command and displays corresponding message to the user.
     * @param manager the task manager in charge of handling task operations
     * @param ui the UI instance for printing messages
     * @param storage the storage instance responsible for saving tasks
     * @throws DukeException if command is deemed invalid or the tasks could not be saved
     */
    public abstract void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if and only if the command is an exit command.
     * @return true if command is an exit command, and false otherwise
     */
    public abstract boolean isExit();
}