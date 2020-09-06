package alice.command;

import java.util.List;

import alice.command.result.ClearCommandResult;
import alice.command.result.CommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.TaskList;

/**
 * Represents the command to clear all the tasks in ALICE.
 */
public class ClearCommand implements Command {
    protected static final List<String> NAMES = List.of("clear", "clr");
    protected static final String DESCRIPTION = "Clear all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the {@code ClearCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code ClearCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    public static ClearCommand createCommand() {
        return new ClearCommand();
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        String clearSuccessMessage = "All tasks successfully cleared!";
        try {
            tasks.clearAllTasks();
            storageFile.save(tasks.encode());
            return new ClearCommandResult(clearSuccessMessage, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new ClearCommandResult(clearSuccessMessage, true, SaveStatus.SAVE_FAILED);
        }
    }
}
