package alice.command;

import java.util.List;

import alice.command.result.ByeCommandResult;
import alice.command.result.CommandResult;
import alice.storage.StorageFile;
import alice.task.TaskList;

/**
 * Represents the command to exit the ALICE program.
 */
public class ByeCommand implements Command {
    protected static final List<String> NAMES = List.of("bye", "exit");
    protected static final String DESCRIPTION = "Exits ALICE program";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the {@code ByeCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code ByeCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    public static ByeCommand createCommand() {
        return new ByeCommand();
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        return new ByeCommandResult("Goodbye. Hope to see you again soon!", true);
    }
}
