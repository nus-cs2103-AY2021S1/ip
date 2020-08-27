package alice.command;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;

import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

/**
 * Represents the command to clear all the tasks in ALICE.
 */
public class ClearCommand extends Command {
    protected static final List<String> NAMES = List.of("clear", "clr");
    protected static final String DESCRIPTION = "Clear all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the <code>ClearCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>ClearCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * {@inheritDoc}
     *
     * @throws AliceStorageException if there were errors writing to storageFile.
     */
    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) throws AliceStorageException {
        String confirmation = ui.getFeedback("Are you sure you want to clear all tasks? [Y/N]");
        ui.displayLine();

        if (confirmation.trim().toLowerCase().startsWith("y")) {
            tasks.clearAllTasks();
            storageFile.save(tasks.encode());
            ui.displayOutput("All tasks successfully cleared!");
        } else {
            // abort
            ui.displayOutput("Clear command aborted!");
        }
    }
}
