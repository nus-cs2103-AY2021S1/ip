package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Wrapper class for all types of Command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     * @throws DukeException If the is a problem with the command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Method to assert that the arguments supplied to a command execution are valid.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     */
    public void assertArgumentsValid(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
