package alice.command;

import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.DoneCommandResult;
import alice.command.result.InvalidCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Task;
import alice.task.TaskList;

/**
 * Represents the command to mark a task as done in ALICE.
 */
public class DoneCommand implements Command {
    protected static final List<String> NAMES = List.of("done");
    protected static final String DESCRIPTION = "Mark a task as done";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <task number>";

    private final int taskIndex;

    /**
     * Creates a new command to mark the indicated task as done.
     *
     * @param taskIndex the index indicating the completed task to mark as done.
     */
    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Checks if the command word triggers the {@code DoneCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code DoneCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new command to mark the task indicated by the user as done.
     *
     * @param inputIndex he string representing the task number given by user.
     * @return the {@code DoneCommand} with the verified task number.
     * @throws InvalidCommandException if the task number provided is invalid.
     */
    public static DoneCommand createCommand(String inputIndex) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(inputIndex) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Don't play around. Give me a proper number!");
        }
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        String reply = "";
        try {
            Task completedTask = tasks.markTaskAsDone(taskIndex);
            reply = "Great work! I've marked this task as done:\n    " + completedTask;
            storageFile.save(tasks.encode());
            return new DoneCommandResult(reply, true, SaveStatus.SAVE_SUCCESS);
        } catch (InvalidCommandException ex) {
            String errorMessage = "Failed to mark task as done.\n" + ex.getMessage();
            return new InvalidCommandResult(errorMessage);
        } catch (AliceStorageException ex) {
            return new DoneCommandResult(reply, true, SaveStatus.SAVE_FAILED);
        }
    }
}
