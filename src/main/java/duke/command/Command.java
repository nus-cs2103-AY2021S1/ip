package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
import duke.task.TaskList;

/**
 * Encapsulates a Command.
 */
public abstract class Command {

    protected final String args;

    protected Command(String args) {
        this.args = args;
    }

    /**
     * Sends a signal to exit program.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes logic based on the given parameters.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return A message to indicate results.
     * @throws TaskException If exception happened while processing task.
     * @throws StorageException If exception happened while storing.
     */
    public abstract String execute(TaskList taskList, Storage storage)
            throws TaskException, StorageException;
}
