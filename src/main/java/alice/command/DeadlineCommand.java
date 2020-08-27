package alice.command;

import alice.task.Deadline;
import alice.task.Task;
import alice.task.TaskList;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;

import alice.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the command to add a new deadline in ALICE.
 */
public class DeadlineCommand extends Command {
    protected static final List<String> NAMES = List.of("deadline");
    protected static final String DESCRIPTION = "Create a task with a deadline. Example: deadline assignment /by 10-Aug 2359";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc> /by <datetime>";

    /**
     * Checks if the command word triggers the <code>DeadlineCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>DeadlineCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new command to create a new <code>Deadline</code> with the details provided.
     *
     * @param description the description of the deadline.
     * @param by the due datetime of the deadline.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     *
     * @throws AliceStorageException if there were errors writing to storageFile.
     */
    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) throws AliceStorageException {
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.displayOutput("Roger. I've added the deadline to your list:\n    " + deadline
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storageFile.saveToLastLine(deadline.encode());
    }
}
