package alice.command;

import alice.AliceException;
import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command recognised by ALICE.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks       the list of tasks stored in ALICE.
     * @param ui          the ALICE user interface.
     * @param storageFile the file used to store ALICE data.
     * @throws AliceException if there were error executing the command.
     */
    public abstract void process(TaskList tasks, Ui ui, StorageFile storageFile) throws AliceException;

    /**
     * Checks if the command is used to exit the ALICE program.
     *
     * @return true if the command exits the ALICE program; false otherwise.
     */
    public boolean isExitCommand() {
        return false;
    }
}
