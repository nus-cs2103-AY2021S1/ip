package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    protected static final List<String> NAMES = List.of("find", "search");
    protected static final String DESCRIPTION = "Find tasks using keywords";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <keyword(s)>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.find(keywords);
        if (output == null) {
            ui.displayOutput("There are no tasks matching your search.");
        } else {
            ui.displayOutput("Here are the tasks that matches your search:\n" + output);
        }
    }
}
