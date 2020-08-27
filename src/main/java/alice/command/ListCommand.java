package alice.command;

import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

/**
 * Represents the command to list all tasks in ALICE.
 */
public class ListCommand extends Command {
    protected static final List<String> NAMES = List.of("list", "ls");
    protected static final String DESCRIPTION = "Lists all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the <code>ListCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>ListCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) {
        String output = tasks.getAllTasks();
        if (output == null) {
            ui.displayOutput("You have no tasks at the moment.");
        } else {
            ui.displayOutput("Here are the tasks in your list:\n" + output);
        }
    }
}
