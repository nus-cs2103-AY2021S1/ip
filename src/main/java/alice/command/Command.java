package alice.command;

import alice.command.result.CommandResult;
import alice.storage.StorageFile;
import alice.task.TaskList;

/**
 * Represents a command recognised by ALICE.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param tasks       the list of tasks stored in ALICE.
     * @param storageFile the file used to store ALICE data.
     * @return the corresponding {@code CommandResult} with instructions for cleaning up.
     */
    CommandResult process(TaskList tasks, StorageFile storageFile);
}
