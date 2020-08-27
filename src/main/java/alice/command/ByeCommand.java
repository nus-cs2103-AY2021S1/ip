package alice.command;

import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

/**
 * Represents the command to exit the ALICE program.
 */
public class ByeCommand extends Command {
    protected static final List<String> NAMES = List.of("bye", "exit");
    protected static final String DESCRIPTION = "Exits ALICE program";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the <code>ByeCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>ByeCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) {
        ui.displayOutput("Goodbye. Hope to see you again soon!");
    }
}
