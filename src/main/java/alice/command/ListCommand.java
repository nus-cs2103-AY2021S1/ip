package alice.command;

import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.ListCommandResult;
import alice.storage.StorageFile;
import alice.task.TaskList;

/**
 * Represents the command to list all tasks in ALICE.
 */
public class ListCommand implements Command {
    protected static final List<String> NAMES = List.of("list", "ls");
    protected static final String DESCRIPTION = "Lists all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the {@code ListCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code ListCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    public static ListCommand createCommand() {
        return new ListCommand();
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        String output = tasks.getAllTasks();
        if (output == null) {
            return new ListCommandResult("You have no tasks at the moment.", true);
        } else {
            return new ListCommandResult("Here are the tasks in your list:\n" + output, true);
        }
    }
}
