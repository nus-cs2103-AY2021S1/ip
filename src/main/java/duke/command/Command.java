package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException If the command cannot be completed.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns boolean indicating program exit status.
     *
     * @return true if program should exit after running this command.
     */
    public boolean isExit() {
        return false;
    }
}
