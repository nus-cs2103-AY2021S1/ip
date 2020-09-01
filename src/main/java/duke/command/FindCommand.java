package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a {@link Command} that will find all tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /**
     * The keyword to search for.
     */
    private final String keyword;

    /**
     * Instantiates a FindCommand with the provided keyword.
     * @param keyword Provided keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to display all tasks containing the given keyword.
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.showMatchingTasks(keyword);
    }
}
