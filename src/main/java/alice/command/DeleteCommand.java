package alice.command;

import java.util.List;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents the command to delete a task from ALICE.
 */
public class DeleteCommand extends Command {
    protected static final List<String> NAMES = List.of("delete");
    protected static final String DESCRIPTION = "Delete a task";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <task number>";

    private final int taskIndex;

    /**
     * Creates a new command to delete the indicated task.
     *
     * @param taskIndex the index indicating the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Checks if the command word triggers the <code>DeleteCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>DeleteCommand</code>; false otherwise.
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
        try {
            Task deletedTask = tasks.deleteTask(taskIndex);
            ui.displayOutput("Roger. I've removed this task from your list:\n    "
                    + deletedTask
                    + "\nNow you have " + tasks.getNumberOfTasks() + " task in your list");
            storageFile.save(tasks.encode());
        } catch (InvalidCommandException ex) {
            ui.displayWarning("Failed to delete task. " + ex.getMessage());
        }
    }
}
