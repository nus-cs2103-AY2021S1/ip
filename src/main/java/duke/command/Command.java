package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Executes an operation.
     * @param taskList TaskList associated with the operation.
     * @param ui Ui responsible for the operation.
     * @param storage Storage associated with the operation.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException;

    /**
     * Indicates whether operation should continue running.
     * @return true.
     */
    public boolean isRunning() {
        return true;
    }
}
