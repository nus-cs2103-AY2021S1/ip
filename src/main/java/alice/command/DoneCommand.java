package alice.command;

import java.util.List;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents the command to mark a task as done in ALICE.
 */
public class DoneCommand extends Command {
    protected static final List<String> NAMES = List.of("done");
    protected static final String DESCRIPTION = "Mark a task as done";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <task number>";

    private final int taskIndex;

    /**
     * Creates a new command to mark the indicated task as done.
     *
     * @param taskIndex the index indicating the completed task to mark as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Checks if the command word triggers the <code>DoneCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>DoneCommand</code>; false otherwise.
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
            Task completedTask = tasks.markTaskAsDone(taskIndex);
            ui.displayOutput("Great work! I've marked this task as done:\n    " + completedTask);
            storageFile.save(tasks.encode());
        } catch (InvalidCommandException ex) {
            ui.displayWarning("Failed to mark task as done. " + ex.getMessage());
        }
    }
}
