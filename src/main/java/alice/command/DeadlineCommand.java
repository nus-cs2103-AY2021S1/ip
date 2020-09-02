package alice.command;

import java.time.LocalDateTime;
import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.DeadlineCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Deadline;
import alice.task.Task;
import alice.task.TaskList;

/**
 * Represents the command to add a new deadline in ALICE.
 */
public class DeadlineCommand implements Command {
    protected static final List<String> NAMES = List.of("deadline");
    protected static final String DESCRIPTION = "Create a task with deadline";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES)
            + "] <desc> /by <datetime>";

    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new command to create a new <code>Deadline</code> with the details provided.
     *
     * @param description the description of the deadline.
     * @param by          the due datetime of the deadline.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Checks if the command word triggers the <code>DeadlineCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>DeadlineCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        String reply = "Roger. I've added the deadline to your list:\n    "
                + deadline + "\nNow you have " + tasks.getNumberOfTasks()
                + " tasks in your list!";
        try {
            storageFile.saveToLastLine(deadline.encode());
            return new DeadlineCommandResult(reply, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new DeadlineCommandResult(reply, true, SaveStatus.SAVE_FAILED);
        }
    }
}
