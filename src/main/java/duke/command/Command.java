package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates the command class
 */
public abstract class Command {

    /**
     * Returns false on exit
     * @return False
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command
     * @param tasks Task given
     * @param ui Ui given
     * @param storage Storage given
     * @throws DukeException If the task cannot be executed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
