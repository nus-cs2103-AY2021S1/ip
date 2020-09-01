package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the find command to search for task with specific keyword in its description
 */
public class FindCommand extends Command {
    private final String keyword; // Also need to ALLCAP this?

    /**
     * Initializes command with given keyword
     * @param keyword The string to be used when filtering tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters and prints the tasks containing the keyword
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) { // TODO: FIX THIS!
        TaskList filteredTasks = new TaskList(tasks.filter(keyword));
        ui.showFilterMessage(filteredTasks.size() > 0, filteredTasks);
    }
}