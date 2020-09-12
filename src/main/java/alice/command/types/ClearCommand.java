package alice.command.types;

import java.util.List;

import alice.command.InvalidCommandException;
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
    protected static final String DESCRIPTION = "Clear all tasks or completed tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] [all, done]";

    private static final String CLEAR_ALL = "ALL";
    private static final String CLEAR_DONE = "DONE";

    private final String type;

    /**
     * Creates a new command to clear tasks from the list.
     * Depending on the user input, it clears all tasks or only completed tasks.
     *
     * @param type the string representing the type of clear to execute.
     */
    private ClearCommand(String type) {
        this.type = type;
    }

    /**
     * Checks if the command word triggers the {@code ClearCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code ClearCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new command to clear tasks from the list.
     *
     * @param argument the string representing the type of clear to execute.
     * @return the {@code ClearCommand} with the specified type.
     * @throws InvalidCommandException if the type of clear execution is invalid.
     */
    public static ClearCommand createCommand(String argument) throws InvalidCommandException {
        assert argument != null : "Argument passed to ClearCommand::createCommand is not null";

        argument = argument.toUpperCase();
        if (argument.equals(CLEAR_ALL)) {
            return new ClearCommand(CLEAR_ALL);
        }

        if (argument.equals(CLEAR_DONE)) {
            return new ClearCommand(CLEAR_DONE);
        }

        throw new InvalidCommandException("Invalid clear type!\nUse either 'clear ALL' or 'clear DONE'");
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        String clearSuccessMessage;
        if (type.equals(CLEAR_ALL)) {
            tasks.clearAllTasks();
            clearSuccessMessage = "All tasks successfully cleared!";
        } else {
            tasks.clearCompletedTasks();
            clearSuccessMessage = "Completed tasks successfully cleared!";
        }

        try {
            storageFile.save(tasks.encode());
            return new ClearCommandResult(clearSuccessMessage, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new ClearCommandResult(clearSuccessMessage, true, SaveStatus.SAVE_FAILED);
        }
    }
}
