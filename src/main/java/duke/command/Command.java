package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command that is being received by Duke.
 */
public abstract class Command {

    /**
     * Executes the given command and returns corresponding message to the user.
     *
     * @param manager The task manager in charge of handling task operations.
     * @param ui The UI instance for printing messages.
     * @param storage The storage instance responsible for saving tasks.
     * @return The string representation of the response by Duke after executing the command.
     * @throws DukeException If command is deemed invalid or the tasks could not be saved.
     */
    public abstract String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException;
}
