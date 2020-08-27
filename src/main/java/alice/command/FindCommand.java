package alice.command;

import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

/**
 * Represents the command to find specific tasks in ALICE.
 */
public class FindCommand extends Command {
    protected static final List<String> NAMES = List.of("find", "search");
    protected static final String DESCRIPTION = "Find tasks using keywords";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <keyword(s)>";

    /**
     * Checks if the command word triggers the <code>FindCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>FindCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String keywords;

    /**
     * Creates a new command to search for tasks based on keywords.
     *
     * @param keywords the string of keywords to search against.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storage) {
        String output = tasks.find(keywords);
        if (output == null) {
            ui.displayOutput("There are no tasks matching your search.");
        } else {
            ui.displayOutput("Here are the tasks that matches your search:\n" + output);
        }
    }
}
