package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Wrapper class for all types of Command.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException If the is a problem with the command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether the command is an exit command.
     * @return Whether is command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
