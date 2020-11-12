package duke.command;

import duke.backend.Storage;
import duke.response.Response;
import duke.task.TaskList;

/**
 * Represents the command to Tasks that contains the keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Class constructor.
     *
     * @param keyword The key word to be searched for.
     */
    public FindCommand(String keyword) {
        assert(keyword != null && keyword != "");
        this.keyword = keyword;
    }

    /**
     * Performs an action of searching the tasks by key word.
     * Prints out the list of task containing the keyword.
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui for Duke.
     * @param storage The Storage for Duke.
     * @return True because the program will continue running.
     */
    @Override
    public String execute(TaskList tasks, Response ui, Storage storage) {
        return (tasks.getTaskWithKeyword(keyword));
    }
}
